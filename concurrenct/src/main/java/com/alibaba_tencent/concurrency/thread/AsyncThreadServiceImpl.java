package com.alibaba_tencent.concurrency.thread;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncThreadServiceImpl implements AsyncThreadService{

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void execute() {

       threadPoolExecutor.execute(() -> {
           try {
               System.out.println(Thread.currentThread().getName() + "线程开始等待...");
               TimeUnit.SECONDS.sleep(8);
               System.out.println(Thread.currentThread().getName() + "线程等待结束！");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });
    }

}
