package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.MedicalHistory.FamilyMemberHistoryCondition;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistoryConditionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberHistoryConditionRepository extends JpaRepository<FamilyMemberHistoryCondition, FamilyMemberHistoryConditionId> {
}
