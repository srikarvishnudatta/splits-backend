package com.splits.backend.dtos;

import com.splits.backend.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class TransactionResponseDto {
    private Map<String, List<Double>> expensesMap;
    private List<Transaction> transactions;
}
