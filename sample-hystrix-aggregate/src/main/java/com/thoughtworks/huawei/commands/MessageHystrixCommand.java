package com.thoughtworks.huawei.commands;

import com.thoughtworks.huawei.domain.Message;
import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import com.thoughtworks.huawei.service.MessageSender;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHystrixCommand extends HystrixCommand<MessageAcknowledgement> {
    private static final String COMMAND_GROUP = "demo";
    private static final Logger logger = LoggerFactory.getLogger(MessageHystrixCommand.class);

    private final MessageSender messageSender;
    private final Message message;

    public MessageHystrixCommand(MessageSender messageSender, Message message) {
        super(HystrixCommandGroupKey.Factory.asKey(COMMAND_GROUP));
        this.messageSender = messageSender;
        this.message = message;
    }

    @Override
    protected MessageAcknowledgement run() throws Exception {
        logger.info("About to make Remote Call");
        return this.messageSender.sendMessage(this.message);
    }

    @Override
    protected MessageAcknowledgement getFallback() {
        return new MessageAcknowledgement(message.getId(), message.getPayload(), "Fallback message");
    }
}
