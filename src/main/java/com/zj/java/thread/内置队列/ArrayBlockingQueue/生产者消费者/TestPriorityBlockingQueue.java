//package com.zj.java.thread.内置队列.ArrayBlockingQueue.生产者消费者;
//
//import sun.misc.SharedSecrets;
//
//import java.util.*;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.PriorityBlockingQueue;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.function.Consumer;
//
///**
// * @Author: zhoujun
// * @Date: 2023/2/18 22:15
// */
//@SuppressWarnings("unchecked")
//public class TestPriorityBlockingQueue<E> extends AbstractQueue<E>
//        implements BlockingQueue<E>, java.io.Serializable {
//    private static final long serialVersionUID = 5595510919245408276L;
//
//    /**
//     * 默认的初始数组容量
//     */
//    private static final int DEFAULT_INITIAL_CAPACITY = 11;
//
//    /**
//     * 最大分配的数组容量
//     * 减 8，防止部分操作系统会默认在一些数组的地址分配中添加一些头信息
//     * 如果直接设置为 Integer.MAX_VALUE 容易发生内存溢出
//     */
//    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
//
//    /**
//     * 优先级队列表示为平衡的二进制堆：queue[n] 的两个子级是队列 queue[2*n+1] 和队列 queue[2*(n+1)]。
//     * 优先级队列按比较器或元素的自然顺序排序，
//     * 如果比较器为 null：对于堆中的每个节点 n 和 n 的每个后代 d，n <= d。
//     * 具有最低值的元素在队列 [0] 中，假设队列为非空。
//     */
//    private transient Object[] queue;
//
//    /**
//     * 队列的元素数量
//     */
//    private transient int size;
//
//    /**
//     * 比较器，如果优先级队列使用元素的自然排序，则为 null。
//     */
//    private transient Comparator<? super E> comparator;
//
//    /**
//     * lock 独占锁对象用来控制同时只能有一个线程可以进行入队、出队操作。
//     */
//    private final ReentrantLock lock;
//
//    /**
//     * 空时的阻塞条件
//     * 条件变量用来实现 take 方法阻塞模式
//     * 没有 notFull 条件变量 是因为这里的 put 操作是非阻塞的，
//     * 为啥要设计为非阻塞的，是因为这是无界队列。
//     */
//    private final Condition notEmpty;
//
//    /**
//     * 用于分配的自旋锁，通过 CAS 获取。
//     * 其使用 CAS 操作来保证同时只有一个线程可以扩容队列，
//     * 状态为 0 或者 1，其中 0 表示当前没有进行扩容，1 表示当前正在扩容。
//     * Spinlock for allocation, acquired via CAS.
//     */
//    private transient volatile int allocationSpinLock;
//
//    /**
//     * 仅用于序列化的纯 PriorityQueue，以保持与此类的早期版本的兼容性。
//     * 仅在序列化反序列化期间为非 null。
//     */
//    private PriorityQueue<E> q;
//
//    /**
//     * Creates a {@code PriorityBlockingQueue} with the default
//     * initial capacity (11) that orders its elements according to
//     * their {@linkplain Comparable natural ordering}.
//     */
//    public TestPriorityBlockingQueue() {
//        this(DEFAULT_INITIAL_CAPACITY, null);
//    }
//
//    /**
//     * Creates a {@code PriorityBlockingQueue} with the specified
//     * initial capacity that orders its elements according to their
//     * {@linkplain Comparable natural ordering}.
//     *
//     * @param initialCapacity the initial capacity for this priority queue
//     * @throws IllegalArgumentException if {@code initialCapacity} is less
//     *                                  than 1
//     */
//    public TestPriorityBlockingQueue(int initialCapacity) {
//        this(initialCapacity, null);
//    }
//
//    /**
//     * Creates a {@code PriorityBlockingQueue} with the specified initial
//     * capacity that orders its elements according to the specified
//     * comparator.
//     *
//     * @param initialCapacity the initial capacity for this priority queue
//     * @param comparator      the comparator that will be used to order this
//     *                        priority queue.  If {@code null}, the {@linkplain Comparable
//     *                        natural ordering} of the elements will be used.
//     * @throws IllegalArgumentException if {@code initialCapacity} is less
//     *                                  than 1
//     */
//    public TestPriorityBlockingQueue(int initialCapacity,
//                                     Comparator<? super E> comparator) {
//        if (initialCapacity < 1)
//            throw new IllegalArgumentException();
//        this.lock = new ReentrantLock();
//        this.notEmpty = lock.newCondition();
//        this.comparator = comparator;
//        this.queue = new Object[initialCapacity];
//    }
//
//    public TestPriorityBlockingQueue(Collection<? extends E> c) {
//        this.lock = new ReentrantLock();
//        this.notEmpty = lock.newCondition();
//        boolean heapify = true; // true if not known to be in heap order
//        boolean screen = true;  // true if must screen for nulls
//        if (c instanceof SortedSet<?>) {
//            SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
//            this.comparator = (Comparator<? super E>) ss.comparator();
//            heapify = false;
//        } else if (c instanceof TestPriorityBlockingQueue<?>) {
//            TestPriorityBlockingQueue<? extends E> pq =
//                    (TestPriorityBlockingQueue<? extends E>) c;
//            this.comparator = (Comparator<? super E>) pq.comparator();
//            screen = false;
//            if (pq.getClass() == TestPriorityBlockingQueue.class) // exact match
//                heapify = false;
//        }
//        Object[] a = c.toArray();
//        int n = a.length;
//        if (c.getClass() != java.util.ArrayList.class)
//            a = Arrays.copyOf(a, n, Object[].class);
//        if (screen && (n == 1 || this.comparator != null)) {
//            for (int i = 0; i < n; ++i)
//                if (a[i] == null)
//                    throw new NullPointerException();
//        }
//        this.queue = a;
//        this.size = n;
//        if (heapify)
//            heapify();
//    }
//
//    /**
//     * tryGrow 的作用是扩容。
//     * <p>这里为啥在扩容前要先释放锁，然后使用CAS控制只有一个线程可以扩容成功？
//     * 其实这里不先释放锁，也是可行的，也就是在整个扩容期间一直持有锁，
//     * 但是扩容是需要花时间的，如果扩容时还占用锁那么其他线程在这个时候是不能进行出队和入队操作的，
//     * 这大大降低了并发性。所以为了提高性能，使用 CAS 控制只有一个线程可以进行扩容，
//     * 并且在扩容前释放锁，让其他线程可以进行入队和出队操作。<p/>
//     * <p> spinlock锁 使用 CAS 控制只有一个线程可以进行扩容，
//     * CAS 失败的线程会调用 Thread.yield() 让出 CPU，目的是让扩容线程扩容后优先调用 lock.lock 重新获取锁，
//     * 但是这得不到保证。有可能 yield 的线程在扩容线程扩容完成前已经退出，
//     * 并执行代码（6）获取到了锁，这时候获取到锁的线程发现 newArray 为 null 就会执行代码（1）。
//     * 如果当前数组扩容还没完毕，当前线程会再次调用 tryGrow 方法，然后释放锁，
//     * 这又给扩容线程获取锁提供了机会，
//     * 如果这时候扩容线程还没扩容完毕，则当前线程释放锁后又调用 yield 方法让出CPU。
//     * 所以当扩容线程进行扩容时，其他线程原地自旋通过代码（1）检查当前扩容是否完毕，
//     * 扩容完毕后才退出代码（1）的循环。<p/>
//     *
//     * @param array  待扩容的原始数组
//     * @param oldCap 原始数组的容量
//     */
//    private void tryGrow(Object[] array, int oldCap) {
//        lock.unlock(); // 释放获取的锁
//        Object[] newArray = null;
//        // (4) CAS成功则扩容
//        /**
//         * 扩容线程扩容完毕后会重置自旋锁变量 allocationSpinLock 为 0，
//         * 这里并没有使用 UNSAFE 方法的 CAS 进行设置是因为同时只可能有一个线程获取到该锁，
//         * 并且 allocationSpinLock 被修饰为了 volatile 的。
//         * 当扩容线程扩容完毕后会执行代码（6）获取锁，获取锁后复制当前 queue 里面的元素到新数组。
//         */
//        if (allocationSpinLock == 0 &&
//                UNSAFE.compareAndSwapInt(this, allocationSpinLockOffset,
//                        0, 1)) {
//            try {
//                // oldGap < 64 则扩容，执行 oldcap + 2，否则扩容 50%，并且最大为 MAX_ARRAY_SIZE
//                int newCap = oldCap + ((oldCap < 64) ?
//                        (oldCap + 2) : // grow faster if small
//                        (oldCap >> 1));
//                if (newCap - MAX_ARRAY_SIZE > 0) {    // possible overflow
//                    int minCap = oldCap + 1;
//                    if (minCap < 0 || minCap > MAX_ARRAY_SIZE)
//                        throw new OutOfMemoryError();
//                    newCap = MAX_ARRAY_SIZE;
//                }
//                if (newCap > oldCap && queue == array)
//                    newArray = new Object[newCap];
//            } finally {
//                allocationSpinLock = 0;
//            }
//        }
//        // (5) 第一个线程 CAS 成功后，第二个线程会进入这段代码，然后第二个线程让出 CPU，
//        // 尽量让第一个线程获取锁，但是这得不到保证。
//        if (newArray == null) // back off if another thread is allocating
//            Thread.yield();
//        lock.lock(); // (6)
//        if (newArray != null && queue == array) {
//            queue = newArray;
//            System.arraycopy(array, 0, newArray, 0, oldCap);
//        }
//    }
//
//    /**
//     * Mechanics for poll().  Call only while holding lock.
//     */
//    private E dequeue() {
//        int n = size - 1;
//        if (n < 0)
//            return null;
//        else {
//            Object[] array = queue;
//            E result = (E) array[0];
//            E x = (E) array[n];
//            array[n] = null;
//            Comparator<? super E> cmp = comparator;
//            if (cmp == null)
//                siftDownComparable(0, x, array, n);
//            else
//                siftDownUsingComparator(0, x, array, n, cmp);
//            size = n;
//            return result;
//        }
//    }
//
//    private static <T> void siftUpComparable(int k, T x, Object[] array) {
//        Comparable<? super T> key = (Comparable<? super T>) x;
//        // 队列元素个数大于 0， 则判断插入位置，否则直接入队。（7）
//        while (k > 0) {
//            int parent = (k - 1) >>> 1;
//            Object e = array[parent];
//            if (key.compareTo((T) e) >= 0)
//                break;
//            array[k] = e;
//            k = parent;
//        }
//        array[k] = key;
//    }
//
//    private static <T> void siftUpUsingComparator(int k, T x, Object[] array,
//                                                  Comparator<? super T> cmp) {
//        while (k > 0) {
//            int parent = (k - 1) >>> 1;
//            Object e = array[parent];
//            if (cmp.compare(x, (T) e) >= 0)
//                break;
//            array[k] = e;
//            k = parent;
//        }
//        array[k] = x;
//    }
//
//    /**
//     * Inserts item x at position k, maintaining heap invariant by
//     * demoting x down the tree repeatedly until it is less than or
//     * equal to its children or is a leaf.
//     *
//     * @param k     the position to fill
//     * @param x     the item to insert
//     * @param array the heap array
//     * @param n     heap size
//     */
//    private static <T> void siftDownComparable(int k, T x, Object[] array,
//                                               int n) {
//        if (n > 0) {
//            Comparable<? super T> key = (Comparable<? super T>) x;
//            int half = n >>> 1;           // loop while a non-leaf
//            while (k < half) {
//                int child = (k << 1) + 1; // assume left child is least
//                Object c = array[child];
//                int right = child + 1;
//                if (right < n &&
//                        ((Comparable<? super T>) c).compareTo((T) array[right]) > 0)
//                    c = array[child = right];
//                if (key.compareTo((T) c) <= 0)
//                    break;
//                array[k] = c;
//                k = child;
//            }
//            array[k] = key;
//        }
//    }
//
//    private static <T> void siftDownUsingComparator(int k, T x, Object[] array,
//                                                    int n,
//                                                    Comparator<? super T> cmp) {
//        if (n > 0) {
//            int half = n >>> 1;
//            while (k < half) {
//                int child = (k << 1) + 1;
//                Object c = array[child];
//                int right = child + 1;
//                if (right < n && cmp.compare((T) c, (T) array[right]) > 0)
//                    c = array[child = right];
//                if (cmp.compare(x, (T) c) <= 0)
//                    break;
//                array[k] = c;
//                k = child;
//            }
//            array[k] = x;
//        }
//    }
//
//    /**
//     * Establishes the heap invariant (described above) in the entire tree,
//     * assuming nothing about the order of the elements prior to the call.
//     */
//    private void heapify() {
//        Object[] array = queue;
//        int n = size;
//        int half = (n >>> 1) - 1;
//        Comparator<? super E> cmp = comparator;
//        if (cmp == null) {
//            for (int i = half; i >= 0; i--)
//                siftDownComparable(i, (E) array[i], array, n);
//        } else {
//            for (int i = half; i >= 0; i--)
//                siftDownUsingComparator(i, (E) array[i], array, n, cmp);
//        }
//    }
//
//    /**
//     * Inserts the specified element into this priority queue.
//     *
//     * @param e the element to add
//     * @return {@code true} (as specified by {@link Collection#add})
//     * @throws ClassCastException   if the specified element cannot be compared
//     *                              with elements currently in the priority queue according to the
//     *                              priority queue's ordering
//     * @throws NullPointerException if the specified element is null
//     */
//    public boolean add(E e) {
//        return offer(e);
//    }
//
//    /**
//     * offer 操作的作用是在队列中插入一个元素，由于是无界队列，所以一直返回 true。
//     *
//     * @param e the element to add
//     * @return {@code true} (as specified by {@link Queue#offer})
//     * @throws ClassCastException   if the specified element cannot be compared
//     *                              with elements currently in the priority queue according to the
//     *                              priority queue's ordering
//     * @throws NullPointerException if the specified element is null
//     */
//    public boolean offer(E e) {
//        if (e == null)
//            throw new NullPointerException();
//        // 获取独占锁
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        int n, cap;
//        Object[] array;
//        // (1) 如果当前元素个数>=队列容量，则扩容
//        while ((n = size) >= (cap = (array = queue).length))
//            tryGrow(array, cap);
//        try {
//            Comparator<? super E> cmp = comparator;
//            // (2) 默认比较器为null
//            if (cmp == null)
//                siftUpComparable(n, e, array);
//            else
//                // (3) 自定义比较器
//                siftUpUsingComparator(n, e, array, cmp);
//            //（9）将队列元素数增加1，并且激活 notEmpty 的条件队列里面的一个阻塞线程
//            size = n + 1;
//            notEmpty.signal(); //激活因调用 take() 方法被阻塞的线程
//        } finally {
//            // 释放独占锁
//            lock.unlock();
//        }
//        return true;
//    }
//
//    /**
//     * Inserts the specified element into this priority queue.
//     * As the queue is unbounded, this method will never block.
//     *
//     * @param e the element to add
//     * @throws ClassCastException   if the specified element cannot be compared
//     *                              with elements currently in the priority queue according to the
//     *                              priority queue's ordering
//     * @throws NullPointerException if the specified element is null
//     */
//    public void put(E e) {
//        offer(e); // never need to block
//    }
//
//    /**
//     * Inserts the specified element into this priority queue.
//     * As the queue is unbounded, this method will never block or
//     * return {@code false}.
//     *
//     * @param e       the element to add
//     * @param timeout This parameter is ignored as the method never blocks
//     * @param unit    This parameter is ignored as the method never blocks
//     * @return {@code true} (as specified by
//     * {@link BlockingQueue#offer(Object, long, TimeUnit) BlockingQueue.offer})
//     * @throws ClassCastException   if the specified element cannot be compared
//     *                              with elements currently in the priority queue according to the
//     *                              priority queue's ordering
//     * @throws NullPointerException if the specified element is null
//     */
//    public boolean offer(E e, long timeout, TimeUnit unit) {
//        return offer(e); // never need to block
//    }
//
//    public E poll() {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            return dequeue();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public E take() throws InterruptedException {
//        final ReentrantLock lock = this.lock;
//        lock.lockInterruptibly();
//        E result;
//        try {
//            while ((result = dequeue()) == null)
//                notEmpty.await();
//        } finally {
//            lock.unlock();
//        }
//        return result;
//    }
//
//    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
//        long nanos = unit.toNanos(timeout);
//        final ReentrantLock lock = this.lock;
//        lock.lockInterruptibly();
//        E result;
//        try {
//            while ((result = dequeue()) == null && nanos > 0)
//                nanos = notEmpty.awaitNanos(nanos);
//        } finally {
//            lock.unlock();
//        }
//        return result;
//    }
//
//    public E peek() {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            return (size == 0) ? null : (E) queue[0];
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Returns the comparator used to order the elements in this queue,
//     * or {@code null} if this queue uses the {@linkplain Comparable
//     * natural ordering} of its elements.
//     *
//     * @return the comparator used to order the elements in this queue,
//     * or {@code null} if this queue uses the natural
//     * ordering of its elements
//     */
//    public Comparator<? super E> comparator() {
//        return comparator;
//    }
//
//    public int size() {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            return size;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Always returns {@code Integer.MAX_VALUE} because
//     * a {@code PriorityBlockingQueue} is not capacity constrained.
//     *
//     * @return {@code Integer.MAX_VALUE} always
//     */
//    public int remainingCapacity() {
//        return Integer.MAX_VALUE;
//    }
//
//    private int indexOf(Object o) {
//        if (o != null) {
//            Object[] array = queue;
//            int n = size;
//            for (int i = 0; i < n; i++)
//                if (o.equals(array[i]))
//                    return i;
//        }
//        return -1;
//    }
//
//    /**
//     * Removes the ith element from queue.
//     */
//    private void removeAt(int i) {
//        Object[] array = queue;
//        int n = size - 1;
//        if (n == i) // removed last element
//            array[i] = null;
//        else {
//            E moved = (E) array[n];
//            array[n] = null;
//            Comparator<? super E> cmp = comparator;
//            if (cmp == null)
//                siftDownComparable(i, moved, array, n);
//            else
//                siftDownUsingComparator(i, moved, array, n, cmp);
//            if (array[i] == moved) {
//                if (cmp == null)
//                    siftUpComparable(i, moved, array);
//                else
//                    siftUpUsingComparator(i, moved, array, cmp);
//            }
//        }
//        size = n;
//    }
//
//    /**
//     * Removes a single instance of the specified element from this queue,
//     * if it is present.  More formally, removes an element {@code e} such
//     * that {@code o.equals(e)}, if this queue contains one or more such
//     * elements.  Returns {@code true} if and only if this queue contained
//     * the specified element (or equivalently, if this queue changed as a
//     * result of the call).
//     *
//     * @param o element to be removed from this queue, if present
//     * @return {@code true} if this queue changed as a result of the call
//     */
//    public boolean remove(Object o) {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            int i = indexOf(o);
//            if (i == -1)
//                return false;
//            removeAt(i);
//            return true;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Identity-based version for use in Itr.remove
//     */
//    void removeEQ(Object o) {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            Object[] array = queue;
//            for (int i = 0, n = size; i < n; i++) {
//                if (o == array[i]) {
//                    removeAt(i);
//                    break;
//                }
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Returns {@code true} if this queue contains the specified element.
//     * More formally, returns {@code true} if and only if this queue contains
//     * at least one element {@code e} such that {@code o.equals(e)}.
//     *
//     * @param o object to be checked for containment in this queue
//     * @return {@code true} if this queue contains the specified element
//     */
//    public boolean contains(Object o) {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            return indexOf(o) != -1;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Returns an array containing all of the elements in this queue.
//     * The returned array elements are in no particular order.
//     *
//     * <p>The returned array will be "safe" in that no references to it are
//     * maintained by this queue.  (In other words, this method must allocate
//     * a new array).  The caller is thus free to modify the returned array.
//     *
//     * <p>This method acts as bridge between array-based and collection-based
//     * APIs.
//     *
//     * @return an array containing all of the elements in this queue
//     */
//    public Object[] toArray() {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            return Arrays.copyOf(queue, size);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public String toString() {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            int n = size;
//            if (n == 0)
//                return "[]";
//            StringBuilder sb = new StringBuilder();
//            sb.append('[');
//            for (int i = 0; i < n; ++i) {
//                Object e = queue[i];
//                sb.append(e == this ? "(this Collection)" : e);
//                if (i != n - 1)
//                    sb.append(',').append(' ');
//            }
//            return sb.append(']').toString();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * @throws UnsupportedOperationException {@inheritDoc}
//     * @throws ClassCastException            {@inheritDoc}
//     * @throws NullPointerException          {@inheritDoc}
//     * @throws IllegalArgumentException      {@inheritDoc}
//     */
//    public int drainTo(Collection<? super E> c) {
//        return drainTo(c, Integer.MAX_VALUE);
//    }
//
//    /**
//     * @throws UnsupportedOperationException {@inheritDoc}
//     * @throws ClassCastException            {@inheritDoc}
//     * @throws NullPointerException          {@inheritDoc}
//     * @throws IllegalArgumentException      {@inheritDoc}
//     */
//    public int drainTo(Collection<? super E> c, int maxElements) {
//        if (c == null)
//            throw new NullPointerException();
//        if (c == this)
//            throw new IllegalArgumentException();
//        if (maxElements <= 0)
//            return 0;
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            int n = Math.min(size, maxElements);
//            for (int i = 0; i < n; i++) {
//                c.add((E) queue[0]); // In this order, in case add() throws.
//                dequeue();
//            }
//            return n;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Atomically removes all of the elements from this queue.
//     * The queue will be empty after this call returns.
//     */
//    public void clear() {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            Object[] array = queue;
//            int n = size;
//            size = 0;
//            for (int i = 0; i < n; i++)
//                array[i] = null;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Returns an array containing all of the elements in this queue; the
//     * runtime type of the returned array is that of the specified array.
//     * The returned array elements are in no particular order.
//     * If the queue fits in the specified array, it is returned therein.
//     * Otherwise, a new array is allocated with the runtime type of the
//     * specified array and the size of this queue.
//     *
//     * <p>If this queue fits in the specified array with room to spare
//     * (i.e., the array has more elements than this queue), the element in
//     * the array immediately following the end of the queue is set to
//     * {@code null}.
//     *
//     * <p>Like the {@link #toArray()} method, this method acts as bridge between
//     * array-based and collection-based APIs.  Further, this method allows
//     * precise control over the runtime type of the output array, and may,
//     * under certain circumstances, be used to save allocation costs.
//     *
//     * <p>Suppose {@code x} is a queue known to contain only strings.
//     * The following code can be used to dump the queue into a newly
//     * allocated array of {@code String}:
//     *
//     * <pre> {@code String[] y = x.toArray(new String[0]);}</pre>
//     * <p>
//     * Note that {@code toArray(new Object[0])} is identical in function to
//     * {@code toArray()}.
//     *
//     * @param a the array into which the elements of the queue are to
//     *          be stored, if it is big enough; otherwise, a new array of the
//     *          same runtime type is allocated for this purpose
//     * @return an array containing all of the elements in this queue
//     * @throws ArrayStoreException  if the runtime type of the specified array
//     *                              is not a supertype of the runtime type of every element in
//     *                              this queue
//     * @throws NullPointerException if the specified array is null
//     */
//    public <T> T[] toArray(T[] a) {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            int n = size;
//            if (a.length < n)
//                // Make a new array of a's runtime type, but my contents:
//                return (T[]) Arrays.copyOf(queue, size, a.getClass());
//            System.arraycopy(queue, 0, a, 0, n);
//            if (a.length > n)
//                a[n] = null;
//            return a;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Returns an iterator over the elements in this queue. The
//     * iterator does not return the elements in any particular order.
//     *
//     * <p>The returned iterator is
//     * <a href="package-summary.html#Weakly"><i>weakly consistent</i></a>.
//     *
//     * @return an iterator over the elements in this queue
//     */
//    public Iterator<E> iterator() {
//        return new TestPriorityBlockingQueue<E>.Itr(toArray());
//    }
//
//    /**
//     * Snapshot iterator that works off copy of underlying q array.
//     */
//    final class Itr implements Iterator<E> {
//        final Object[] array; // Array of all elements
//        int cursor;           // index of next element to return
//        int lastRet;          // index of last element, or -1 if no such
//
//        Itr(Object[] array) {
//            lastRet = -1;
//            this.array = array;
//        }
//
//        public boolean hasNext() {
//            return cursor < array.length;
//        }
//
//        public E next() {
//            if (cursor >= array.length)
//                throw new NoSuchElementException();
//            lastRet = cursor;
//            return (E) array[cursor++];
//        }
//
//        public void remove() {
//            if (lastRet < 0)
//                throw new IllegalStateException();
//            removeEQ(array[lastRet]);
//            lastRet = -1;
//        }
//    }
//
//    /**
//     * Saves this queue to a stream (that is, serializes it).
//     * <p>
//     * For compatibility with previous version of this class, elements
//     * are first copied to a java.util.PriorityQueue, which is then
//     * serialized.
//     *
//     * @param s the stream
//     * @throws java.io.IOException if an I/O error occurs
//     */
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws java.io.IOException {
//        lock.lock();
//        try {
//            // avoid zero capacity argument
//            q = new PriorityQueue<E>(Math.max(size, 1), comparator);
//            q.addAll(this);
//            s.defaultWriteObject();
//        } finally {
//            q = null;
//            lock.unlock();
//        }
//    }
//
//    /**
//     * Reconstitutes this queue from a stream (that is, deserializes it).
//     *
//     * @param s the stream
//     * @throws ClassNotFoundException if the class of a serialized object
//     *                                could not be found
//     * @throws java.io.IOException    if an I/O error occurs
//     */
//    private void readObject(java.io.ObjectInputStream s)
//            throws java.io.IOException, ClassNotFoundException {
//        try {
//            s.defaultReadObject();
//            int sz = q.size();
//            SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, sz);
//            this.queue = new Object[sz];
//            comparator = q.comparator();
//            addAll(q);
//        } finally {
//            q = null;
//        }
//    }
//
//    // Similar to Collections.ArraySnapshotSpliterator but avoids
//    // commitment to toArray until needed
//    static final class PBQSpliterator<E> implements Spliterator<E> {
//        final TestPriorityBlockingQueue<E> queue;
//        Object[] array;
//        int index;
//        int fence;
//
//        PBQSpliterator(TestPriorityBlockingQueue<E> queue, Object[] array,
//                       int index, int fence) {
//            this.queue = queue;
//            this.array = array;
//            this.index = index;
//            this.fence = fence;
//        }
//
//        final int getFence() {
//            int hi;
//            if ((hi = fence) < 0)
//                hi = fence = (array = queue.toArray()).length;
//            return hi;
//        }
//
//        public Spliterator<E> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid) ? null :
//                    new TestPriorityBlockingQueue.PBQSpliterator<E>(queue, array, lo, index = mid);
//        }
//
//        @SuppressWarnings("unchecked")
//        public void forEachRemaining(java.util.function.Consumer<? super E> action) {
//            Object[] a;
//            int i, hi; // hoist accesses and checks from loop
//            if (action == null)
//                throw new NullPointerException();
//            if ((a = array) == null)
//                fence = (a = queue.toArray()).length;
//            if ((hi = fence) <= a.length &&
//                    (i = index) >= 0 && i < (index = hi)) {
//                do {
//                    action.accept((E) a[i]);
//                } while (++i < hi);
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super E> action) {
//            if (action == null)
//                throw new NullPointerException();
//            if (getFence() > index && index >= 0) {
//                @SuppressWarnings("unchecked") E e = (E) array[index++];
//                action.accept(e);
//                return true;
//            }
//            return false;
//        }
//
//        public long estimateSize() {
//            return (long) (getFence() - index);
//        }
//
//        public int characteristics() {
//            return Spliterator.NONNULL | Spliterator.SIZED | Spliterator.SUBSIZED;
//        }
//    }
//
//    /**
//     * Returns a {@link Spliterator} over the elements in this queue.
//     *
//     * <p>The returned spliterator is
//     * <a href="package-summary.html#Weakly"><i>weakly consistent</i></a>.
//     *
//     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
//     * {@link Spliterator#NONNULL}.
//     *
//     * @return a {@code Spliterator} over the elements in this queue
//     * @implNote The {@code Spliterator} additionally reports {@link Spliterator#SUBSIZED}.
//     * @since 1.8
//     */
//    public Spliterator<E> spliterator() {
//        return new TestPriorityBlockingQueue.PBQSpliterator<E>(this, null, 0, -1);
//    }
//
//    // Unsafe mechanics
//    private static final sun.misc.Unsafe UNSAFE;
//    private static final long allocationSpinLockOffset;
//
//    static {
//        try {
//            UNSAFE = sun.misc.Unsafe.getUnsafe();
//            Class<?> k = PriorityBlockingQueue.class;
//            allocationSpinLockOffset = UNSAFE.objectFieldOffset
//                    (k.getDeclaredField("allocationSpinLock"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
//    }
//}
