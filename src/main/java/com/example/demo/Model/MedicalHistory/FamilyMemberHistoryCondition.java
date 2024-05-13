package com.example.demo.Model.MedicalHistory;

import com.example.demo.Model.MedicalCondition.ClnCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLN_FAMILY_MEMBER_HX_CONDITION")
public class FamilyMemberHistoryCondition {

    @EmbeddedId
    FamilyMemberHistoryConditionId familyMemberHistoryConditionId;


    @ManyToOne
    @MapsId("familyMemberHxId")
    @JoinColumn(name = "FAMILY_MEMBER_HX_ID")
    private FamilyMemberHistory familyMemberHistory;

    //@Id
    @ManyToOne
    @MapsId("conditionId")
    @JoinColumn(name = "CONDITION_ID")
    private ClnCondition clnCondition;

    public FamilyMemberHistoryCondition(FamilyMemberHistory familyMemberHistory, ClnCondition clnCondition) {
        this.familyMemberHistoryConditionId=new FamilyMemberHistoryConditionId(familyMemberHistory.getFamilyMemberHxId(),clnCondition.getConditionId());
        this.familyMemberHistory = familyMemberHistory;
        this.clnCondition = clnCondition;
    }
}
