package com.alibaba_tencent.concurrency.singleton;

/**
 * @Desc: 枚举实现单例
 *        优点：线程安全，不用担心序列化与反射问题
 *        缺点：枚举占用的内存会多一点
 * @Param:
 * @Return:
 * @Author: ChenYong
 * @Date: 2020-04-16 10:11
 */
public enum EnumSingleton {

    SINGLETON;

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String filed) {
        this.field = filed;
    }

}
