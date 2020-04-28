package com.alibaba_tencent.concurrency.base;

/**
 * @ClassName: AutoIncrement
 * @Desc:  自增 练习
 * @Author: Chen Yong
 * @Date: 2020-04-24 13:07
 */
public class AutoIncrement {

    public static void main(String[] args) {

        int i = 6;
        i = i++;  // i = 6
        int j = i++; // i = 7, j = 6
        int k = i+ ++i * i++; // k = 7 + (8 * 8) , i = 7 + 1 + 1
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
