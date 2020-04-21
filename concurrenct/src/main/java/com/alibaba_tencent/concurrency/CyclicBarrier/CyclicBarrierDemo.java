package com.alibaba_tencent.concurrency.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierDemo
 * @Desc: CyclicBarrier类的使用Demo，助记：收集七龙珠，才可以召唤神龙。或 人到齐了，才能开会
 * @Author: Chen Yong
 * @Date: 2020-04-17 15:10
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
            () -> System.out.println("*********召唤神龙********")
    );

    public static void main(String[] args) {

        for (int i = 1; i <=7 ; i++) {
            int tempInt = i;
            new Thread(() -> {
                System.out.println("第" + tempInt + "颗龙珠已收集");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
