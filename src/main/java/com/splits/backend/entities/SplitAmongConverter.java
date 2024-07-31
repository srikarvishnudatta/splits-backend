package com.splits.backend.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class SplitAmongConverter implements AttributeConverter<Map<String, Double>, String> {
    public final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map<String, Double> stringDoubleMap) {
        try{
            return objectMapper.writeValueAsString(stringDoubleMap);
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot write json", e);
        }
    }

    @Override
    public Map<String, Double> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot convert back to json", e);
        }
    }
}
