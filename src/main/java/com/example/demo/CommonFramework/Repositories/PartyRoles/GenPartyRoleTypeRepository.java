package com.example.demo.CommonFramework.Repositories.PartyRoles;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenPartyRoleTypeRepository extends JpaRepository<GenPartyRoleType,Long> {
    GenPartyRoleType findByRoleName(String roleName);
}
