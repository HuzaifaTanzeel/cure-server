package com.example.demo.CommonFramework.DTO;

public record HCResponseDTO(
        Long partyId,
        Long partyRoleTypeId,
        Long hcProvOrgId,
        int partyTypeCd,
        String providerName,
        int orgTypeCd,
        int practiceRoleCd,
        byte[] logo
) {
}
