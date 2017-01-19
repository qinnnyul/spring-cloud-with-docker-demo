package com.thoughtworks.huawei.controller;


import com.thoughtworks.huawei.domain.Message;
import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import com.thoughtworks.huawei.service.MessageHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class MessageResource {

    private final MessageHandlerService messageHandlerService;

    @Autowired
    public MessageResource(MessageHandlerService messageHandlerService) {
        this.messageHandlerService = messageHandlerService;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public DeferredResult<MessageAcknowledgement> sendMessage(@RequestBody Message input) {
        DeferredResult<MessageAcknowledgement> deferred = new DeferredResult<>();
        this.messageHandlerService.handleMessage(input)
                .subscribe(res -> deferred.setResult(res), e -> deferred.setErrorResult(e));

        return deferred;
    }

}
