package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.GenPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenPersonRepository extends JpaRepository<GenPerson,Long> {

    GenPerson findByPartyId(Long personPid);
}
