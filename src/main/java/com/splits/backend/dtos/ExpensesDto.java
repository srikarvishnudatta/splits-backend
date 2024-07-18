package com.splits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ExpensesDto {
    private String expensesId;
    private Map<String, List<Double>> expensesMap;
}
