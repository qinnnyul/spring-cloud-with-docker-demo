package com.thoughtworks.huawei.service;

import com.thoughtworks.huawei.domain.Message;
import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("sampleservice")
public interface MessageSender {

    @RequestMapping(method = RequestMethod.POST, value = "/message",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    MessageAcknowledgement sendMessage(@RequestBody Message message);
}