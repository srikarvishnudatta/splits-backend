package com.splits.backend.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class ExpensesMapConverter implements AttributeConverter<Map<String, Map<String, Double>>, String> {
    public final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map<String, Map<String, Double>> attribute) {
        try{
            return objectMapper.writeValueAsString(attribute);
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot write json", e);
        }
    }

    @Override
    public Map<String, Map<String, Double>> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Map<String, Map<String, Double>>>() {});
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot convert back to json", e);
        }
    }
}
