package com.alibaba_tencent.concurrency.jvm;

import java.lang.ref.WeakReference;

/**
 * @ClassName: WeakReferenceDemo
 * @Desc:   弱引用
 * @Author: Chen Yong
 * @Date: 2020-04-22 15:23
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {

        weakRefTest();
    }

    public static void weakRefTest() {

        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());

        o = null;
        System.gc();

        System.out.println("=============");


        System.out.println(o);
        System.out.println(weakReference.get());

    }
}
