package com.alibaba_tencent.concurrency.singleton;

/**
 * @ClassName: LockLazySingleton
 * @Desc: 加锁懒汉式
 * @Author: Chen Yong
 * @Date: 2020-04-16 9:32
 */
public class LockLazySingleton {

    private static LockLazySingleton singleton = null;

    private LockLazySingleton() {
        System.out.println(Thread.currentThread().getName() + "加锁懒汉式创建单例，创建成功");
    }

    public static synchronized LockLazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LockLazySingleton();
        }
        return singleton;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> getInstance(), "线程：" + i).start();
        }
    }
}
