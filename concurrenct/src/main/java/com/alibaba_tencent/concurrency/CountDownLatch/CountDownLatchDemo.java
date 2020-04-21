package com.alibaba_tencent.concurrency.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchDemo
 * @Desc: CountDownLatch Demo 助记：六国灭，秦才能统一！
 * @Author: Chen Yong
 * @Date: 2020-04-17 14:28
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        destroyCountry();

        // closeDoor();
    }

    private static void destroyCountry() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国被灭了");
                countDownLatch.countDown();
            }, CountryEnum.foreachEnum(i).getName()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ---------------秦灭六国，一统华夏-------------");
    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ---------------班长最后关门走人-------------");
    }
}
