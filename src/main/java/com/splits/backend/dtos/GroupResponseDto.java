package com.splits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GroupResponseDto {
    private String groupId;

    private String name;

    private String createdAt;
    private List<String> groupMembers;
}
