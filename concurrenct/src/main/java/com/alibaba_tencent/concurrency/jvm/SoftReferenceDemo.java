package com.alibaba_tencent.concurrency.jvm;

import java.lang.ref.SoftReference;

/**
 * @ClassName: SoftReferenceDemo
 * @Desc:  软引用 使用Demo
 * @Author: Chen Yong
 * @Date: 2020-04-22 15:01
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        // softRefEnoughMemory();
        softRefNotEnoughMemory();
    }

    /**
     * @Desc: 模拟内存够用的情况下，软引用的对象时否会被回收
     * @Author: ChenYong
     * @Date: 2020-04-22 15:04
     */
    public static void softRefEnoughMemory() {

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        System.gc();

        o1 = null;
        System.out.println(o1);
        System.out.println(softReference.get());

    }


    /**
     * @Desc: 模拟内存不足的情况下，软引用的对象是否会被回收
     *          -Xms10m -Xmx10m -XX:+PrintGCDetails
     * @Author: ChenYong
     * @Date: 2020-04-22 15:14
     */
    public static void softRefNotEnoughMemory() {

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {

            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }




}
