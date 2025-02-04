package com.bank.Banking.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {
    CREDIT,DEBIT;
    @JsonCreator
    public static TransactionType fromString(String value) {
        return TransactionType.valueOf(value.toUpperCase());
    }

}
