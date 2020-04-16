package com.alibaba_tencent.concurrency.cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: CASAtomicReference
 * @Desc: 模拟ABA问题，并解决ABA问题
 * @Author: Chen Yong
 * @Date: 2020-04-16 16:22
 */
public class CASAtomicReference {

    public static void main(String[] args) {


        User user1 = new User("张三", 25);
        User user2 = new User("李四", 30);

        System.out.println("------------------模拟产生ABA问题-----------------");


        AtomicReference<User> reference1 = new AtomicReference<>();
        reference1.set(user1);

        new Thread(() -> {
            boolean compareAndSet = reference1.compareAndSet(user1, user2);
            System.out.println(Thread.currentThread().getName() + "\t" + compareAndSet);
        }, "t1").start();

        new Thread(() -> {
            boolean compareAndSet = reference1.compareAndSet(user2, user1);
            System.out.println(Thread.currentThread().getName() + "\t" + compareAndSet);
        }, "t2").start();


        System.out.println("-----------------------模拟解决ABA问题--------------------------");

        AtomicStampedReference<User> reference2 = new AtomicStampedReference<>(user1, 1);

        int stamp = reference2.getStamp();

        new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean compareAndSet = reference2.compareAndSet(user1, user2, stamp, reference2.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + compareAndSet);
        }, "t3").start();

        new Thread(() -> {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean compareAndSet = reference2.compareAndSet(user2, user1, stamp, reference2.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + compareAndSet);
        }, "t4").start();


    }


}
