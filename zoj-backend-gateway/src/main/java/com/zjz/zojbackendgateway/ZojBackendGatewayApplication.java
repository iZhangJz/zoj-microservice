package com.zjz.zojbackendgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 排除数据源自动配置
@EnableDiscoveryClient
public class ZojBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZojBackendGatewayApplication.class, args);
    }

}
