package com.zj.java.thread.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @Author: zhoujun
 * @Date: 2023/2/8 08:41
 */
public class TestUnsafe {
    // 获取 Unsafe 的实例（2.2.1）
    static final Unsafe unsafe;
    // 记录变量 state 在类 TestUnSafe 中的偏移值（2.2.2）
    static final long stateOffset;
    // 变量(2.2.3)
    private volatile long state = 0;

    static final long objectOffSet;

    private volatile TestUnsafe object;

    static {
        try {
            // 使用反射获取 Unsafe 的成员变量 theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置为可存取
            field.setAccessible(true);
            // 获取该变量的值
            unsafe = (Unsafe) field.get(null);
            // 获取 state 在 TestUnSafe中 的偏移量
            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.
                    getDeclaredField("state"));
            objectOffSet = unsafe.objectFieldOffset(TestUnsafe.class.
                    getDeclaredField("object"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        // 创建实例，并且设置 state 值为 1 (2.2.5)
        TestUnsafe test = new TestUnsafe();
        test.object = new TestUnsafe();
        TestUnsafe test2 = new TestUnsafe();
        test2.object = test;
        TestUnsafe test3 = new TestUnsafe();
        // (2.2.6)
        Boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
        System.out.println(test.state);
        boolean success1 = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println("success1 = " + success1 + "  data = " + test.state);
        boolean success2 = unsafe.compareAndSwapInt(test, stateOffset, 1, 1000);
        System.out.println("success2 = " + success2 + "  data = " + test.state);
        System.out.println(test.object);
        test2.state = 12;
        boolean swap = test.swap(test, test);
        System.out.println(test2);
        System.out.println(swap +"   ===> " + test.object + "   state = " + test.object.state);
    }

    public boolean swap(TestUnsafe com, TestUnsafe val) {
        return unsafe.compareAndSwapObject(this, objectOffSet, com, val);
    }
}
