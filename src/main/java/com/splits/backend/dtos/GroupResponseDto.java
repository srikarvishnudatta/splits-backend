package com.splits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class GroupResponseDto {
    private String groupId;

    private String name;

    private String date;

    private String[] members;
}
