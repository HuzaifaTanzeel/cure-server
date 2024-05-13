package com.example.demo.Enumerators.MedicalHistory;



import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FamilyMemberRelationship {
    SON(2401, "SON", "Son"),
    DAUGHTER(2402, "DAUGHTER", "Daughter"),
    FATHER(2403, "FATHER", "Father"),
    MOTHER(2404, "MOTHER", "Mother"),
    BROTHER(2405, "BROTHER", "Brother"),
    SISTER(2406, "SISTER", "Sister"),
    HUSBAND(2407, "HUSBAND", "Husband"),
    WIFE(2408, "WIFE", "Wife");

    private final int code;
    private final String description;
    private final String display;

}


@Converter(autoApply = true)
class FamilyMemberRelationshipConverter implements AttributeConverter<FamilyMemberRelationship, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FamilyMemberRelationship attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public FamilyMemberRelationship convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (FamilyMemberRelationship relationship : FamilyMemberRelationship.values()) {
            if (dbData.equals(relationship.getCode())) {
                return relationship;
            }
        }


        throw new IllegalArgumentException("Invalid FamilyMemberRelationship code: " + dbData);
    }
}
