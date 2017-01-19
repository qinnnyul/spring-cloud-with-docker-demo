package com.thoughtworks.huawei.controller;

import com.thoughtworks.huawei.commands.MessageHystrixCommand;
import com.thoughtworks.huawei.domain.Message;
import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import com.thoughtworks.huawei.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2")
public class MessageResource {

    @Autowired
    private MessageSender messageSender;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public MessageAcknowledgement sendMessage(@RequestBody Message message) {
        MessageHystrixCommand remoteCallCommand = new MessageHystrixCommand(messageSender, message);
        return remoteCallCommand.execute();
    }
}
