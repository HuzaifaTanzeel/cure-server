package com.example.demo.Repository.AppointmentManagement;

import com.example.demo.Model.AppointmentManagement.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter,Long> {
}
