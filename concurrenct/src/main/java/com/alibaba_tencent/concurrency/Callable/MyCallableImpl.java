package com.alibaba_tencent.concurrency.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *  实现Callable接口  启动线程，获取运行结果
 */
public class MyCallableImpl implements Callable<Integer> {

    @Override
    public Integer call() throws Exception{
        System.out.println("==============come in Callable========");
        return 1024;
    }

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallableImpl());
        Thread thread1 = new Thread(futureTask, "Thread-a");
        thread1.start();

        int a = 100;

        System.out.println(Thread.currentThread().getName() + "====进来了==========");

        Integer result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("最终的结果：" + (a + result));
    }
}


