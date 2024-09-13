package com.vikram.macquarie.bankdemo.common.error;

public enum ErrorCodeEnum implements ErrorCodeIf {

    ACCOUNT_ACCESS_DENIED("ACCOUNT_ACCESS_DENIED", "You do not have permission to access this account.");

    private final String errorCode;
    private final String errorMessage;

    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
