package com.splits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InviteDto {
    String sender;
    String receiver;
    String groupName;
}
