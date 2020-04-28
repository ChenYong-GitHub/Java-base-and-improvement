package com.alibaba_tencent.concurrency.base;

/**
 * @ClassName: ClassInitAndInstanceInitSon
 * @Desc:  类初始化与实例初始化（子类）
 * @Author: Chen Yong
 * @Date: 2020-04-24 13:49
 */
public class ClassInitAndInstanceInitSon extends ClassInitAndInstanceInitFather {

    private int i = test();

    private static int j = method();

    static {
        System.out.println("<6>");
    }

    public ClassInitAndInstanceInitSon() {
        System.out.println("<7>");
    }

    {
        System.out.println("<8>");
    }

    public int test() {
        System.out.println("<9>");
        return 0;
    }

    public static int method() {
        System.out.println("<10>");
        return 0;
    }

    public static void main(String[] args) {
        ClassInitAndInstanceInitSon s1 = new ClassInitAndInstanceInitSon();
        System.out.println();
        ClassInitAndInstanceInitSon s2 = new ClassInitAndInstanceInitSon();
    }

}
