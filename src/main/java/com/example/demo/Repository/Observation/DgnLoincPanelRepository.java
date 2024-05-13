package com.example.demo.Repository.Observation;

import com.example.demo.Model.Observation.DgnLoincPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DgnLoincPanelRepository extends JpaRepository<DgnLoincPanel, String> {

    DgnLoincPanel findByLoincNumber(String loincNum);


}