package com.vikram.macquarie.bankdemo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pagination {
    private int offset;
    private int limit;
    private int totalCount;
}
