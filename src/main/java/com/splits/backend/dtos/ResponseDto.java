package com.splits.backend.dtos;

import lombok.Data;

@Data
public class ResponseDto {
        String id;
        public ResponseDto(String id){
            this.id = id;
        }
}
