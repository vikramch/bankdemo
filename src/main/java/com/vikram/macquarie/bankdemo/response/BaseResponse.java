package com.vikram.macquarie.bankdemo.response;

import com.vikram.macquarie.bankdemo.common.error.Error;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
public class BaseResponse {
    private final Status status;

    @Setter
    private List<Error> errors;

    @Setter
    private Pagination pagination;


    public BaseResponse(Status status) {
        this.status = status;
    }
}
