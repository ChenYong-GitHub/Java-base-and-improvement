package com.alibaba_tencent.concurrency.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WeakHashMapDemo
 * @Desc:
 * @Author: Chen Yong
 * @Date: 2020-04-22 15:57
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        myWeakHashMap();
    }

    private static void myWeakHashMap() {

        HashMap<Integer, String> map = new HashMap<>();

        Integer key = new Integer(1);
        String value = "Hello";

        map.put(key, value);

        System.out.println(map);

        key = null;

        System.out.println(map);
    }
}
