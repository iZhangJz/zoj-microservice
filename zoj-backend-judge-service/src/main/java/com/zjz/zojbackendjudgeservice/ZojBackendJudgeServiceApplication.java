package com.zjz.zojbackendjudgeservice;

import com.zjz.zojbackendserviceclient.service.JudgeFeignClient;
import com.zjz.zojbackendserviceclient.service.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = JudgeFeignClient.class)
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.zjz")
public class ZojBackendJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZojBackendJudgeServiceApplication.class, args);
    }

}
