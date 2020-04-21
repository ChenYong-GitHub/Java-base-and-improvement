package com.alibaba_tencent.concurrency.CountDownLatch;

import lombok.Getter;

public enum CountryEnum {

    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    @Getter private Integer code;
    @Getter private String name;

    CountryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CountryEnum foreachEnum(Integer code) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
