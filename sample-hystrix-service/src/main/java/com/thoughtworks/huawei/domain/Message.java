package com.thoughtworks.huawei.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    private String id;
    private String payload;
    private boolean throwException;
    private int delayBy = 0;

    @JsonCreator
    public Message(@JsonProperty("id") String id,
                   @JsonProperty("payload") String payload,
                   @JsonProperty("throwException") boolean throwException,
                   @JsonProperty("delayBy") int delayBy) {
        this.id = id;
        this.payload = payload;
        this.throwException = throwException;
        this.delayBy = delayBy;
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public int getDelayBy() {
        return delayBy;
    }

}
