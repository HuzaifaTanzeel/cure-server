package com.example.demo.CommonFramework.Repositories.PracticeANDFacility;

import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliation;
import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PractitionerFacilityAffiliationRepo extends JpaRepository<PractitionerFacilityAffiliation, PractitionerFacilityAffiliationId> {

    List<PractitionerFacilityAffiliation> findByPractitionerFacilityAffiliationId_PractitionerPidAndPractitionerFacilityAffiliationId_PartyRoleTypeId(
            Long practitionerPid, Long partyRoleTypeId);

    PractitionerFacilityAffiliation findByPractitionerFacilityAffiliationIdFacilityIdAndPractitionerFacilityAffiliationIdPractitionerPidAndPractitionerFacilityAffiliationIdPartyRoleTypeId(Long facilityId, Long practitionerPid, Long partyRoleTypeId);
    Optional<PractitionerFacilityAffiliation> findByPractitionerFacilityAffiliationId_FacilityId(Long facilityId);

}
