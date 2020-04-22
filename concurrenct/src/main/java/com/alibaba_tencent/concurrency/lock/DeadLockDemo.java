package com.alibaba_tencent.concurrency.lock;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 *  死锁演示 及 定位分析
 *
 *   1、jps 查看有哪些java进程
 *      jps(Java Virtual Machine Process Status Tool)
 *      是java提供的一个显示当前所有java进程pid的命令，适合在linux/unix平台上简单察看当前java进程的一些简单情况。
 *
 *   2、再用  jstack pid  命令查看 对应进程的报错详情
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lock1 = "lockA";
        String lock2 = "lockB";

        MyRunnableImpl myRunnable1 = new MyRunnableImpl(lock1, lock2);
        new Thread(() -> {
            myRunnable1.run();
        }, "Thread-1").start();

        MyRunnableImpl myRunnable2 = new MyRunnableImpl(lock2, lock1);
        new Thread(() -> {
            myRunnable2.run();
        }, "Thread-2").start();
    }
}


@AllArgsConstructor
@Data
class MyRunnableImpl implements Runnable {

    private String lock1;
    private String lock2;

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "获得锁" + lock1 + ",并尝试获取锁" + lock2);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获得锁" + lock2);
            }
        }
    }
}
