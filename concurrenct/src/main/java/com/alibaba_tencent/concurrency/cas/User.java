package com.alibaba_tencent.concurrency.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: User
 * @Desc:
 * @Author: Chen Yong
 * @Date: 2020-04-16 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private Integer age;

}
