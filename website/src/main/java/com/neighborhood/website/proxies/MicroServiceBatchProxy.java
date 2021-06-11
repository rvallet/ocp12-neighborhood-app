package com.neighborhood.website.proxies;

import com.neighborhood.website.dto.MailDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-batch")
@RibbonClient(name = "ms-batch")
public interface MicroServiceBatchProxy {

    @PostMapping(value = "/sendMail")
    void sendMail(@RequestBody MailDto email);

}
