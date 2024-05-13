package com.example.demo.CommonFramework.Repositories.PracticeANDFacility;

import com.example.demo.CommonFramework.Model.Practice.PractitionerHcProvORGReltn;
import com.example.demo.CommonFramework.Model.Practice.PractitionerHcProvORGReltnId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionerHcProvReltnRepo extends JpaRepository<PractitionerHcProvORGReltn, PractitionerHcProvORGReltnId> {

    // Define a method to find a PractitionerHcProvORGReltn by hcProvOrgPid
    PractitionerHcProvORGReltn findByPractitionerHcProvORGReltnIdHcProvOrgPid(Long hcProvOrgPid);

    // Define a method to find a PractitionerHcProvORGReltn by partyId and partyRoleTypeId
    PractitionerHcProvORGReltn findByPractitionerHcProvORGReltnIdPractitionerPidAndPractitionerHcProvORGReltnIdPartyRoleTypeId(Long partyId, Long partyRoleTypeId);



}
