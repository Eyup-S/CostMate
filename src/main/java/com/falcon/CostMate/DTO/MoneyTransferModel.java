package com.falcon.CostMate.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyTransferModel {

    private Long senderId;
    private Long receiverId;
    private Double amount;

    public MoneyTransferModel(Long senderId, Long receiverId, Double amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }
}
