package com.example.demo.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum NoteType {
    SOAP(5901),
    SIMPLE(5902);

    private final int code;

    NoteType(int code) {
        this.code = code;
    }

}

@Converter(autoApply = true)
class NoteTypeConverter implements AttributeConverter<NoteType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NoteType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public NoteType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (NoteType type : NoteType.values()) {
            if (dbData.equals(type.getCode())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid NoteType code: " + dbData);
    }
}
