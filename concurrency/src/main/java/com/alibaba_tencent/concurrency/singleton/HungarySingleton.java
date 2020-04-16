package com.alibaba_tencent.concurrency.singleton;

/**
 * @ClassName: HungarySingleton
 * @Desc: 饿汉式
 * @Author: Chen Yong
 * @Date: 2020-04-16 9:15
 */
public class HungarySingleton {

    private static final HungarySingleton singleton = new HungarySingleton();

    private HungarySingleton() {
        System.out.println(Thread.currentThread().getName() + "饿汉式创建单例，创建成功！");
    }

    public static HungarySingleton getInstance() {
        return singleton;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                getInstance();
            }, "线程：" + i).start();
        }
    }
}
