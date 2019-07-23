package com.fanglin.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启用程序
 * @author 彭方林
 * @date 2019/4/3 14:15
 * @version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.fanglin")
@EnableDubbo
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
