package com.vikram.macquarie.bankdemo.common.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Currency {
    SGD("SGD"),
    AUD("AUD"),
    USD("USD");

    private final String currencyString;

    private Currency(String currencyString) {
        this.currencyString = currencyString;
    }
}
