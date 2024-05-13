package com.example.demo.Model.MedicalHistory;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FamilyMemberHistoryConditionId implements Serializable {

    @Column(name = "FAMILY_MEMBER_HX_ID")
    private Long familyMemberHxId;

    @Column(name = "CONDITION_ID")
    private Long conditionId;

    public FamilyMemberHistoryConditionId(Long familyMemberHxId, Long conditionId) {
        this.familyMemberHxId = familyMemberHxId;
        this.conditionId = conditionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMemberHistoryConditionId that = (FamilyMemberHistoryConditionId) o;
        return Objects.equals(familyMemberHxId, that.familyMemberHxId) && Objects.equals(conditionId, that.conditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyMemberHxId, conditionId);
    }
}
