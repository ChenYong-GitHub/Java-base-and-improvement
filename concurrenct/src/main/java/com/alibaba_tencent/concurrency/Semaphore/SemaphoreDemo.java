package com.alibaba_tencent.concurrency.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @ClassName: SemaphoreDemo
 * @Desc:   信号量/信号灯 助记：抢车位  多辆车 抢 多个车位，也可以抢  一个 车位
 * @Author: Chen Yong
 * @Date: 2020-04-17 15:38
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 9; i++) {
            int tempInt  = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("第" + tempInt + "号抢到了车位");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println("第" + tempInt + "号 3 秒后离开了车位");
            },String.valueOf(i)).start();
        }
    }

}
