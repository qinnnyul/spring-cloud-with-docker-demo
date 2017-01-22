package com.thoughtworks.huawei.commands;

import com.thoughtworks.huawei.domain.Message;
import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import com.thoughtworks.huawei.service.MessageSender;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageAnnotationHystrixCommand {

    private final MessageSender messageSender;

    @Autowired
    public MessageAnnotationHystrixCommand(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @HystrixCommand(fallbackMethod = "defaultMessage", commandKey = "DownstreamServiceMonitoringDemo" )
    public MessageAcknowledgement sendMessage(Message message) {
        return this.messageSender.sendMessage(message);
    }

    public MessageAcknowledgement defaultMessage(Message message) {
        return new MessageAcknowledgement("-1", message.getPayload(), "Fallback Payload");
    }

}
