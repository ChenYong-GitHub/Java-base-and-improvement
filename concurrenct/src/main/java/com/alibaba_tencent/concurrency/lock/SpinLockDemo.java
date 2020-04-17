package com.alibaba_tencent.concurrency.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: SpinLock
 * @Desc:  自旋锁Demo
 * @Author: Chen Yong
 * @Date: 2020-04-17 10:41
 */
public class SpinLockDemo {

    AtomicReference<Thread> ar = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        while (ar.compareAndSet(null, thread)) {

        }
        System.out.println("加上了锁：" + thread.getName());
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        ar.compareAndSet(thread, null);
        System.out.println("释放了锁：" + thread.getName());
    }


    public static void main(String[] args) {
        SpinLockDemo demo = new SpinLockDemo();

        new Thread(() -> {
            demo.lock();

            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            demo.unlock();
        }, "Thread-1").start();

        // try {
        //     // 保证 Thread-1 先进行自旋
        //     Thread.sleep(3000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        new Thread(() -> {
            demo.lock();
            demo.unlock();
        }, "Thread-2").start();
    }
}
