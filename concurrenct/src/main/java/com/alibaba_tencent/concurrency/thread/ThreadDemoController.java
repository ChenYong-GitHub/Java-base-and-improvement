package com.alibaba_tencent.concurrency.thread;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadDemoController {

    @Autowired
    private AsyncThreadService asyncThreadService;

    @GetMapping("/sayHello")
    public String sayHello() {
        System.out.println(Thread.currentThread().getName() + "执行了");
        asyncThreadService.execute();
        System.out.println(Thread.currentThread().getName() + "执行了");

        return "Success";
    }

}
