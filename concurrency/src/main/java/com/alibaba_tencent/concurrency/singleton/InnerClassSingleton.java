package com.alibaba_tencent.concurrency.singleton;

/**
 * @ClassName: InnerClassSingleton
 * @Desc:   静态内部类单例
 *          优点：实现简单，懒加载，线程安全
 *          缺点：增加了一个静态内部类，apk文件增大
 * @Author: Chen Yong
 * @Date: 2020-04-16 10:06
 */
public class InnerClassSingleton {

    private InnerClassSingleton() {
        System.out.println(Thread.currentThread().getName() + "静态内部类创建单例，创建成功");
    }

    private static class InnerClassSingletonInstance {
        private static final InnerClassSingleton singleton = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
        return InnerClassSingletonInstance.singleton;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> getInstance(), "线程：" + i).start();
        }
    }
}
