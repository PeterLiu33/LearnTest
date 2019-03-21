package com.koflance.lt.java.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 缺点：
 * CAS操作需要硬件的配合；
 * 保证各个CPU的缓存（L1、L2、L3、跨CPU Socket、主存）的数据一致性，通讯开销很大，在多处理器系统上更严重；
 * 没法保证公平性，不保证等待进程/线程按照FIFO顺序获得锁。
 * <p>
 * Created by liujun on 2018/3/6.
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread currentThread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者
        while (owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();

        // 只有锁的拥有者才能释放锁
        owner.compareAndSet(currentThread, null);
    }
}