package com.injoin.ijboss.api;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "demoAPI",url = "${address.api.ijboss.demo}")
public interface DemoAPI {

    public String demoString();

}
