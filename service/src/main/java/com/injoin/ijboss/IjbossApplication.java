package com.injoin.ijboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan("com.injoin.ijboss")
@EnableFeignClients(basePackages = {"com.injoin.ijuser.api", "com.injoin.ijflow.api"})
public class IjbossApplication {

    public static void main(String[] args) {
        System.setProperty("io.netty.noUnsafe","true");
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        System.setProperty("spring.devtools.restart.enabled", "false");
        try {
            SpringApplication.run(com.injoin.ijboss.IjbossApplication.class, args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
