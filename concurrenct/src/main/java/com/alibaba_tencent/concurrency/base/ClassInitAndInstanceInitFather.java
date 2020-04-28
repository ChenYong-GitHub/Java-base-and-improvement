package com.alibaba_tencent.concurrency.base;

/**
 * @ClassName: ClassInitAndInstanceInit
 * @Desc:  类初始化与实例初始化（父类）
 * @Author: Chen Yong
 * @Date: 2020-04-24 13:48
 */
public class ClassInitAndInstanceInitFather {

    private int i = test();

    private static int j = method();

    static {
        System.out.println("<1>");
    }

    public ClassInitAndInstanceInitFather() {
        System.out.println("<2>");
    }

    {
        System.out.println("<3>");
    }

    private int test() {
        System.out.println("<4>");
        return 0;
    }

    private static int method() {
        System.out.println("<5>");
        return 0;
    }

}
