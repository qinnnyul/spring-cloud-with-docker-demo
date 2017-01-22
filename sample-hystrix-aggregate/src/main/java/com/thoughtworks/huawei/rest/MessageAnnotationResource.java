package com.thoughtworks.huawei.rest;

import com.thoughtworks.huawei.commands.MessageAnnotationHystrixCommand;
import com.thoughtworks.huawei.domain.Message;
import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api/v1")
public class MessageAnnotationResource {

    @Autowired
    private MessageAnnotationHystrixCommand remoteServiceClient;

    @RequestMapping(value = "/message", method = POST)
    public MessageAcknowledgement sendMessage(@RequestBody Message message) {
        return remoteServiceClient.sendMessage(message);
    }
}
