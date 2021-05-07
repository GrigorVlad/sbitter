package me.grigor.sbitter.response;

import lombok.Getter;

@Getter
public class ServiceResponse<T> {

    private T data;
    private ResultMessages messages;

    public ServiceResponse(T data) {
        this.data = data;
    }

    public ServiceResponse(ResultMessages resultMessages) {
        this.messages = resultMessages;
    }

    public ServiceResponse(T data, ResultMessages messages) {
        this.data = data;
        this.messages = messages;
    }

}
