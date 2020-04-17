package com.alibaba_tencent.concurrency.lock;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @Desc: 可重入锁Demo
 * @Author: Chen Yong
 * @Date: 2020-04-17 8:45
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        Phone iphoneSe = new Phone("iphone SE", new BigDecimal(3299));


        new Thread(() -> {
            iphoneSe.getName();

        }, "t1").start();

        new Thread(() -> {
            iphoneSe.setName("iphone 11 Pro MAX");
        }, "t2").start();

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("------------------优雅的分割线--------------------");
        System.out.println();

        new Thread(() -> {
            iphoneSe.getPrice();
        }, "t3").start();

        new Thread(() -> {
            iphoneSe.setPrice(new BigDecimal(9999));
        }, "t4").start();

    }
}


class Phone {

    public Phone(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private BigDecimal price;
    private Lock lock = new ReentrantLock();

    public synchronized String getName() {
        System.out.println(Thread.currentThread().getName() + "\t getName()");
        setName("");
        return name;
    }

    public synchronized void setName(String name) {
        System.out.println(Thread.currentThread().getName() + "\t setName()");
        this.name = name;
    }


    public BigDecimal getPrice() {
        lock.lock();
        /**
         *   这里可以加任意多把锁，编译不会报错，但必须有相应的unlock动作，否则会死锁！！！
         */
        // lock.lock();
        // lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t getPrice()");
            setPrice(new BigDecimal(3299));
            return price;
        } finally {
            lock.unlock();
            // lock.unlock();
            // lock.unlock();
        }
    }

    public void setPrice(BigDecimal price) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t setPrice()");
            this.price = price;
        } finally {
            lock.unlock();
        }
    }
}