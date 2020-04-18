package com.alibaba_tencent.concurrency.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile、CAS、atomic、blockingQueue、多线程交互 综合案例
 */
public class ComprehensiveBlockingQueueDemo {

    public static void main(String[] args) {

        MySource mySource = new MySource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println("开始生产===================");
            mySource.produce();
        }, "Thread-produce").start();

        new Thread(() -> {
            System.out.println("开始消费==============");
            mySource.consume();
            }, "Thread-consume").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("大老板喊暂停，停止生产");
        mySource.stop();
    }
}


class MySource {

    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;


    public MySource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void produce() {

        try {

            String data;
            boolean offer;
            while (flag) {
                data = atomicInteger.getAndIncrement() + "";
                offer = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println(Thread.currentThread().getName() + "生产了第" + data + "个蛋糕");
                } else {
                    System.out.println(Thread.currentThread().getName() + "生产失败");
                }
                Thread.sleep(100);
            }
            System.out.println("停止生产");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void consume() {

        try {
            String poll;
            while (flag) {
                poll = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (poll == null || poll.equalsIgnoreCase("")) {
                    flag = false;
                    System.out.println("2秒种没有消费到蛋糕");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "消费了第" + poll + "个蛋糕");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        flag = false;
    }
}
