package com.vikram.macquarie.bankdemo.context;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RequestContext {
    private final String userId;

    public RequestContext(String userId) {
        this.userId = userId;
    }
}
