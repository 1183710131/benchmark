package com.alibaba.benchmark;

import com.taobao.pandora.boot.PandoraBootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource(locations = "classpath:hsf-consumer-bean.xml")

public class HSFConsumerApplication {

    public static void main(String[] args) {
        PandoraBootstrap.run(args);
        SpringApplication.run(HSFConsumerApplication.class, args);
        PandoraBootstrap.markStartupAndWait();
    }

}