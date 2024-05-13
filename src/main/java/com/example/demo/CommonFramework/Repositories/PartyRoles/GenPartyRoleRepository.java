package com.example.demo.CommonFramework.Repositories.PartyRoles;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenPartyRoleRepository extends JpaRepository<GenPartyRole, GenPartyRoleId> {

    @Override
    Optional<GenPartyRole> findById(GenPartyRoleId genPartyRoleId);

//    <GenPartyRole>  findBygenPartyRoleId(GenPartyRoleId genPartyRoleId);
}
