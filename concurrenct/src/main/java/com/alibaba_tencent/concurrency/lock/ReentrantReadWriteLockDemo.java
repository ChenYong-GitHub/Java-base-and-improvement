package com.alibaba_tencent.concurrency.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReentrantReadWriteLockDemo
 * @Desc:   可重入读写锁Demo
 * @Author: Chen Yong
 * @Date: 2020-04-17 10:58
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 0; i < 10; i++) {
            final int tempInt = i;
            // 写入操作是被一个线程独占的，一旦写线程开始，其他线程必须等待其它完成后才能继续执行
            new Thread(() -> cache.put("key" + tempInt, "value" + tempInt), "线程-" + tempInt).start();
        }

        for (int i = 0; i < 10; i++) {
            final int tempInt = i;
            // 读操作可以被多个线程持有，其它线程不必等待当前线程操作完成 才操作
            new Thread(() -> cache.get("key" + tempInt), "线程" + (tempInt + 10)).start();
        }
    }

}


/**
 * @Desc: 演示读写锁，模拟缓存类
 * @Author: ChenYong
 * @Date: 2020-04-17 10:58
 */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Object get(String key) {
        lock.readLock().lock();
        Object result = null;

        try {
            System.out.println(Thread.currentThread().getName() + "：正在读取+++");
            Thread.sleep(100);
            result = map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        System.out.println(Thread.currentThread().getName() + "：读取完成---");
        return result;
    }


    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "：正在写入>>>");
            Thread.sleep(100);
            map.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        System.out.println(Thread.currentThread().getName() + "：写入完成<<<");
    }
}