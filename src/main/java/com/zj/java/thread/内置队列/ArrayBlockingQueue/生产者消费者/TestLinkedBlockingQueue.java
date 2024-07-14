package com.zj.java.thread.内置队列.ArrayBlockingQueue.生产者消费者;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-02-17
 * LinkedBlockingQueue 的总结
 * 生产者和消费者并行处理（极端情况，伪结点做了处理）
 * 当队列为空，消费者阻塞
 * 当队列满了，生产者阻塞
 * 当队列满了，消费者消费了以后，唤醒生产者
 * 当队列为空，生产者生产了节点以后，唤醒消费者
 * 其他情况下，都是同胞唤醒同胞
 */
public class TestLinkedBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable {
    private static final long serialVersionUID = -6903933977591709194L;
    /**
     * 执行 take、 poll 等操作时需要获取该锁
     */
    private final ReentrantLock takeLock = new ReentrantLock();
    /**
     * 当队列为空时，执行出队操作（比如 take ）的线程会被放入这个条件队列进行等待
     */
    private final Condition notEmpty = takeLock.newCondition();
    /**
     * 执行 put、 offer 等操作时需要获取该锁
     */
    private final ReentrantLock putLock = new ReentrantLock();
    /**
     * 当队列满时，执行进队操作（比如 put )的线程会被放入这个条件队列进行等待
     */
    private final Condition notFull = putLock.newCondition();

    /**
     * 队列的容量
     */
    private final int capacity;
    /**
     * 当前队列元素个数
     */
    private final AtomicInteger count = new AtomicInteger(0);

    static class Node<E> {
        E item;

        /**
         * One of:
         * - the real successor Node
         * - this Node, meaning the successor is head.next
         * - null, meaning there is no successor (this is the last node)
         */
        Node<E> next;

        Node(E x) {
            item = x;
        }
    }

    /**
     * Head of linked list.
     * Invariant: head.item == null
     */
    transient Node<E> head;

    /**
     * Tail of linked list.
     * Invariant: last.next == null
     */
    private transient Node<E> last;

    /**
     * Signals a waiting take. Called only from put/offer (which do not
     * otherwise ordinarily lock takeLock.)
     */
    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * Signals a waiting put. Called only from take/poll.
     */
    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private void enqueue(Node<E> node) {
        // assert putLock.isHeldByCurrentThread();
        // assert last.next == null;
        last = last.next = node;
    }

    /**
     * Removes a node from head of queue.
     *
     * @return the node
     */
    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        // help GC
        h.next = h;
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    /**
     * 涉及两把锁，都锁住
     * 执行 take、 poll 等操作时需要获取该锁
     * 执行 put, offer 的操作时的锁
     */
    void fullyLock() {
        putLock.lock();
        takeLock.lock();
    }

    /**
     * 涉及两把锁，都解锁
     * 执行 take、 poll 等操作时需要获取该锁
     * 执行 put, offer 的操作时的锁
     */
    void fullyUnlock() {
        takeLock.unlock();
        putLock.unlock();
    }

    /**
     * 默认构造方法
     */
    public TestLinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public TestLinkedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
        last = head = new Node<E>(null);
    }

    public TestLinkedBlockingQueue(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        final ReentrantLock putLock = this.putLock;
        putLock.lock(); // Never contended, but necessary for visibility
        try {
            int n = 0;
            for (E e : c) {
                if (e == null)
                    throw new NullPointerException();
                if (n == capacity)
                    throw new IllegalStateException("Queue full");
                enqueue(new Node<E>(e));
                ++n;
            }
            count.set(n);
        } finally {
            putLock.unlock();
        }
    }

    public int size() {
        return count.get();
    }

    public int remainingCapacity() {
        return capacity - count.get();
    }

    /**
     * 向队列尾部插入一个元素，如果队列中有空闲则插入后直接返回，
     * 如果队列已满则阻塞当前线程，直到队列有空闲插入成功后返回。
     * 如果在阻塞时被其他线程设置了中断标志，则被阻塞线程会抛出 InterruptedException 异常而返回。
     * 另外，如果 e 元素为 null 则抛出 NullPointerException 异常。
     * @param e 待添加的元素
     * @throws InterruptedException 在阻塞时被其他线程设置了中断标志，则被阻塞线程会抛出 InterruptedException 异常而返回。
     */
    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        int c = -1;
        Node<E> node = new Node<E>(e);
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            // 如果队列满了，则阻塞等待。
            while (count.get() == capacity) {
                notFull.await();
            }
            // 入队
            enqueue(node);
            // count.getAndIncrement() 获取的是 count 原始的值，然后再加 1
            c = count.getAndIncrement();
            // 当前容量小于队列容量时，唤醒其他的生产者
            // 判断如果新元素入队后队列还有空闲空间，则唤醒 notFull 的条件队列里面因为调用了 notFull 的 await 操作
            // （比如执行 put 方法而队列满了的时候）而被阻塞的一个线程，因为队列现在有空闲所以这里可以提前唤醒一个入队线程。
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
    }

    public boolean offer(E e, long timeout, TimeUnit unit)
            throws InterruptedException {

        if (e == null) throw new NullPointerException();
        long nanos = unit.toNanos(timeout);
        int c = -1;
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            while (count.get() == capacity) {
                if (nanos <= 0)
                    return false;
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(new Node<E>(e));
            c = count.getAndIncrement();
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
        return true;
    }

    /**
     * 向队列尾部插入一个元素，如果队列中有空闲则插入成功后返回 true，
     * 如果队列已满则丢弃当前元素然后返回 false。
     * 如果 e 元素为 null 则抛出 NullPointerException 异常。
     *
     * 总结：offer 方法通过使用 putLock 锁保证了在队尾新增元素操作的原子性。
     *      另外，调用条件变量的方法前一定要记得获取对应的锁，并且注意进队时只操作队列链表的尾节点。
     * @param e 需要插入的元素
     * @return true 插入成功，false 插入失败
     */
    public boolean offer(E e) {
        if (e == null) throw new NullPointerException();
        final AtomicInteger count = this.count;
        // 队列已满，不允许插入
        if (count.get() == capacity)
            return false;
        int c = -1;
        Node<E> node = new Node<E>(e);
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            if (count.get() < capacity) {
                enqueue(node);
                // count.getAndIncrement() 获取的是 count 原始的值，然后再加 1
                c = count.getAndIncrement();
                // 当前容量小于队列容量时，唤醒其他的生产者
                // 判断如果新元素入队后队列还有空闲空间，则唤醒 notFull 的条件队列里面因为调用了 notFull 的 await 操作
                // （比如执行 put 方法而队列满了的时候）而被阻塞的一个线程，因为队列现在有空闲所以这里可以提前唤醒一个入队线程。
                if (c + 1 < capacity)
                    notFull.signal();
            }
        } finally {
            // (6)
            putLock.unlock();
        }
        // c==0 说明在执行代码（6）释放锁时队列里面至少有一个元素，
        // 队列里面有元素则执行 signalNotEmpty 操作，
        if (c == 0)
            signalNotEmpty();
        return c >= 0;
    }

    /**
     * 获取当前队列头部元素并从队列里面移除它。
     * 如果队列为空则阻塞当前线程直到队列不为空然后返回元素，
     * 如果在阻塞时被其他线程设置了中断标志，则被阻塞线程会抛出 InterruptedException 异常而返回。
     * @return 返回队列头部的元素
     * @throws InterruptedException 如果在阻塞时被其他线程设置了中断标志，则被阻塞线程会抛出 InterruptedException 异常而返回。
     */
    public E take() throws InterruptedException {
        E x;
        int c = -1;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            // 如果队列为空，则需要阻塞等待。
            while (count.get() == 0) {
                notEmpty.await();
            }
            // 出队
            x = dequeue();
            // 获取当前队列的数量,
            c = count.getAndDecrement();
            // 如果此时队列不为空，通知其他阻塞的消费线程，可以进行消费了。
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E x = null;
        int c = -1;
        long nanos = unit.toNanos(timeout);
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            x = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }

    /**
     * 从队列头部获取并移除一个元素，如果队列为空则返回 null，该方法是不阻塞的。
     * poll 代码逻辑比较简单，值得注意的是，获取元素时只操作了队列的头节点。
     * @return 从队头获取到的元素
     */
    public E poll() {
        final AtomicInteger count = this.count;
        // 如果队列为空，直接返回 null
        if (count.get() == 0)
            return null;
        E x = null;
        int c = -1;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            if (count.get() > 0) {
                x = dequeue();
                c = count.getAndDecrement();
                if (c > 1)
                    notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }

    /**
     * 获取队列头部元素但是不从队列里面移除它，如果队列为空则返回null。该方法是不阻塞的。
     * 总结：
     *      由于 remove 方法在删除指定元素前加了两把锁，
     *      所以在遍历队列查找指定元素的过程中是线程安全的，
     *      并且此时其他调用入队、出队操作的线程全部会被阻塞。
     *      另外，获取多个资源锁的顺序与释放的顺序是相反的。
     * @return 返回队列头部的元素
     */
    public E peek() {
        if (count.get() == 0)
            return null;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            Node<E> first = head.next;
            if (first == null)
                return null;
            else
                return first.item;
        } finally {
            takeLock.unlock();
        }
    }

    void unlink(Node<E> p, Node<E> trail) {
        // assert isFullyLocked();
        // p.next is not changed, to allow iterators that are
        // traversing p to maintain their weak-consistency guarantee.
        p.item = null;
        trail.next = p.next;
        if (last == p)
            last = trail;
        // 删除元素后，如果发现当前队列有空闲空间，则唤醒  notFull 的条件队列中的一个因为调用 put 方法而被阻塞的线程。
        if (count.getAndDecrement() == capacity)
            notFull.signal();
    }

    /**
     * 删除队列里面指定的元素，有则删除并返回true，没有则返回false。
     * @param o 要从此队列中删除的元素（如果存在）
     * @return 是否删除成功
     */
    public boolean remove(Object o) {
        if (o == null) return false;
        // 此时不允许插入或获取元素，所以要全部锁住、
        fullyLock();
        try {
            for (Node<E> trail = head, p = trail.next;
                 p != null;
                 trail = p, p = p.next) {
                if (o.equals(p.item)) {
                    unlink(p, trail);
                    return true;
                }
            }
            return false;
        } finally {
            fullyUnlock();
        }
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        fullyLock();
        try {
            for (Node<E> p = head.next; p != null; p = p.next)
                if (o.equals(p.item))
                    return true;
            return false;
        } finally {
            fullyUnlock();
        }
    }

    public Object[] toArray() {
        fullyLock();
        try {
            int size = count.get();
            Object[] a = new Object[size];
            int k = 0;
            for (Node<E> p = head.next; p != null; p = p.next)
                a[k++] = p.item;
            return a;
        } finally {
            fullyUnlock();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        fullyLock();
        try {
            int size = count.get();
            if (a.length < size)
                a = (T[]) java.lang.reflect.Array.newInstance
                        (a.getClass().getComponentType(), size);

            int k = 0;
            for (Node<E> p = head.next; p != null; p = p.next)
                a[k++] = (T) p.item;
            if (a.length > k)
                a[k] = null;
            return a;
        } finally {
            fullyUnlock();
        }
    }

    public String toString() {
        fullyLock();
        try {
            Node<E> p = head.next;
            if (p == null)
                return "[]";

            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (; ; ) {
                E e = p.item;
                sb.append(e == this ? "(this Collection)" : e);
                p = p.next;
                if (p == null)
                    return sb.append(']').toString();
                sb.append(',').append(' ');
            }
        } finally {
            fullyUnlock();
        }
    }

    public void clear() {
        fullyLock();
        try {
            for (Node<E> p, h = head; (p = h.next) != null; h = p) {
                h.next = h;
                p.item = null;
            }
            head = last;
            // assert head.item == null && head.next == null;
            if (count.getAndSet(0) == capacity)
                notFull.signal();
        } finally {
            fullyUnlock();
        }
    }

    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        if (maxElements <= 0)
            return 0;
        boolean signalNotFull = false;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            int n = Math.min(maxElements, count.get());
            // count.get provides visibility to first n Nodes
            Node<E> h = head;
            int i = 0;
            try {
                while (i < n) {
                    Node<E> p = h.next;
                    c.add(p.item);
                    p.item = null;
                    h.next = h;
                    h = p;
                    ++i;
                }
                return n;
            } finally {
                // Restore invariants even if c.add() threw
                if (i > 0) {
                    // assert h.item == null;
                    head = h;
                    signalNotFull = (count.getAndAdd(-i) == capacity);
                }
            }
        } finally {
            takeLock.unlock();
            if (signalNotFull)
                signalNotFull();
        }
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        /*
         * Basic weakly-consistent iterator.  At all times hold the next
         * item to hand out so that if hasNext() reports true, we will
         * still have it to return even if lost race with a take etc.
         */

        private Node<E> current;
        private Node<E> lastRet;
        private E currentElement;

        Itr() {
            fullyLock();
            try {
                current = head.next;
                if (current != null)
                    currentElement = current.item;
            } finally {
                fullyUnlock();
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        private Node<E> nextNode(Node<E> p) {
            for (; ; ) {
                Node<E> s = p.next;
                if (s == p)
                    return head.next;
                if (s == null || s.item != null)
                    return s;
                p = s;
            }
        }

        public E next() {
            fullyLock();
            try {
                if (current == null)
                    throw new NoSuchElementException();
                E x = currentElement;
                lastRet = current;
                current = nextNode(current);
                currentElement = (current == null) ? null : current.item;
                return x;
            } finally {
                fullyUnlock();
            }
        }

        public void remove() {
            if (lastRet == null)
                throw new IllegalStateException();
            fullyLock();
            try {
                Node<E> node = lastRet;
                lastRet = null;
                for (Node<E> trail = head, p = trail.next;
                     p != null;
                     trail = p, p = p.next) {
                    if (p == node) {
                        unlink(p, trail);
                        break;
                    }
                }
            } finally {
                fullyUnlock();
            }
        }
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    }
}
