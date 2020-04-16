package com.alibaba_tencent.concurrency.singleton;

/**
 * @ClassName: DoubleCheckLazySingleton
 * @Desc: 双重检查懒汉式(DCL Double Check Lazy), 在多线程环境下，因为 没有数据依赖关系，可能存在指令重排
 * @Author: Chen Yong
 * @Date: 2020-04-16 9:35
 */
public class DoubleCheckLazySingleton {

    private static DoubleCheckLazySingleton singleton = null;
    // 加上 volatile 可以禁止指令重排，可以保证多线程环境 安全
    // private static volatile DoubleCheckLazySingleton singleton = null;

    private DoubleCheckLazySingleton() {
        System.out.println(Thread.currentThread().getName() + "双重检查创建单例，创建成功");
    }


    // 在多线程环境下，不安全，因为 没有数据依赖关系，可能存在指令重排，可能会取到实例化未完成的对象
    // singleton = new DoubleCheckLazySingleton(); 这句代码可以分为三步完成（伪代码）
    // 1、memory = allocate(); 分配对象内存空间
    // 2、singleton(memory); 初始化对象
    // 3、singleton = memory; 设置singleton指向刚才分配的内存之地，此时singleton != null

    // 步骤2和3 不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变
    // 因此 这种重排优化是允许的
    // 1、memory = allocate(); 分配对象内存空间
    // 3、singleton = memory; 设置singleton指向刚才分配的内存之地，此时singleton != null
    // 2、singleton(memory); 初始化对象
    // 但是指令重排只会保证串行语义的执行的一致性（单线程），但并不关心多线程键的语义一致性，
    // 所以当一条线程访问singleton不为null时，由于singleton实例未必已经初始化完成，所以可能获取的实例还没有完成初始化，也就造成了线程安全问题。

    // private static volatile DoubleCheckLazySingleton singleton = null;
    // 加上 volatile, 可以保证多线程安全，因为volatile禁止指令重排
    public static DoubleCheckLazySingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckLazySingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckLazySingleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> getInstance(), "线程：" + i).start();
        }
    }
}
