package com.splits.backend.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class MemberListConverter implements AttributeConverter<List<String>, String> {
    public final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        try{
            return objectMapper.writeValueAsString(strings);
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot write json", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        try {
            return objectMapper.readValue(string, new TypeReference<List<String>>() {});
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot convert back to json", e);
        }
    }
}
