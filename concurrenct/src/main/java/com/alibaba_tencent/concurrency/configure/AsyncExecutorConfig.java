package com.alibaba_tencent.concurrency.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
//@EnableAsync
@Slf4j
public class AsyncExecutorConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(99999),
                new DefaultManagedAwareThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
