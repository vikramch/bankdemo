package com.vikram.macquarie.bankdemo.common.error;

import lombok.Getter;

@Getter
public class Error {

    private final String errorCode;
    private final String errorMessage;
    private final String errorDetail;

    public Error(ErrorCodeIf errorCode, String errorDetail) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
        this.errorDetail = errorDetail;
    }
}
