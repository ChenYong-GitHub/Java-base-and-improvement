package com.alibaba_tencent.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  多线程 精准唤醒
 */
public class AccuracyNotify {

    public static void main(String[] args) {

        ShareSource shareSource = new ShareSource();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                shareSource.print5();
            }, "Thread-1").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                shareSource.print10();
            }, "Thread-2").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                shareSource.print15();
            }, "Thread-3").start();
        }
    }
}


class ShareSource{

    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {

        lock.lock();
        try {
            // 1、判断
            while (num != 1) {
                c1.await();
            }
            // 2、干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " 打印：" + i);
            }
            num = 2;
            // 3、通知
            c2.signal();
        } catch (InterruptedException e) {
                e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    public void print10() {

        lock.lock();
        try {
            // 1、判断
            while (num != 2) {
                c2.await();
            }
            // 2、干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " 打印：" + i);
            }
            num = 3;
            // 3、通知
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {

        lock.lock();
        try {
            // 1、判断
            while (num != 3) {
                c3.await();
            }
            // 2、干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + " 打印：" + i);
            }
            num = 1;
            // 3、通知
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
