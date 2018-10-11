package com.injoin.ijboss.client;

import in.togetu.tsuser.api.AccountAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "tsUserAccountAPI",url = "${address.api.tsuser.user}")
public interface TsUserAccountAPI extends AccountAPI {
}
