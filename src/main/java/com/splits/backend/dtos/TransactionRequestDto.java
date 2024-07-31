package com.splits.backend.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TransactionRequestDto {
    private String transactionName;
    private Double transactionValue;
    private String paidBy;
    private Map<String, Double> splitAmong;
}
