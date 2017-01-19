package com.thoughtworks.huawei.service;

import com.thoughtworks.huawei.domain.MessageAcknowledgement;
import com.thoughtworks.huawei.domain.Message;
import rx.Observable;

public interface MessageHandlerService {
    Observable<MessageAcknowledgement> handleMessage(Message message);
}
