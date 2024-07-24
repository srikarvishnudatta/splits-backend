package com.splits.backend.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Converter
public class ExpensesMapConverter implements AttributeConverter<Map<String, List<Double>>, String> {
    public final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map<String, List<Double>> attribute) {
        try{
            return objectMapper.writeValueAsString(attribute);
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot write json", e);
        }
    }

    @Override
    public Map<String, List<Double>> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Map<String, List<Double>>>() {});
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot convert back to json", e);
        }
    }
}
