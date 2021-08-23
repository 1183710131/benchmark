package com.alibaba.edas;

import com.taobao.pandora.boot.PandoraBootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class HSFConsumerApplication {

    public static void main(String[] args) {
        PandoraBootstrap.run(args);
        SpringApplication.run(HSFConsumerApplication.class, args);
        PandoraBootstrap.markStartupAndWait();
    }

}