package com.vikram.macquarie.bankdemo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pagination {
    private int totalCount;
    private int pageSize;
    private int pageNumber;
}
