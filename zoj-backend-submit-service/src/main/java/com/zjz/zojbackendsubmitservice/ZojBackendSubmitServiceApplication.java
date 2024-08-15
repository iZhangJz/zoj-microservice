package com.zjz.zojbackendsubmitservice;

import com.zjz.zojbackendserviceclient.service.QuestionSubmitFeignClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = QuestionSubmitFeignClient.class)
@MapperScan("com.zjz.zojbackendsubmitservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.zjz")
public class ZojBackendSubmitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZojBackendSubmitServiceApplication.class, args);
    }

}
