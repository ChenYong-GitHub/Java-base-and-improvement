package com.alibaba_tencent.concurrency.singleton;

/**
 * @ClassName: LazySingleton
 * @Desc:  懒汉式
 * @Author: Chen Yong
 * @Date: 2020-04-16 9:19
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + "懒汉式创建单例，创建成功！");
    }

    public static LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() ->
                LazySingleton.getInstance()
            ,"线程：" + i).start();
        }
    }
}
