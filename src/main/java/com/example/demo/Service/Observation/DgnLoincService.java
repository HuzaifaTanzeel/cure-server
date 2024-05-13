package com.example.demo.Service.Observation;

import com.example.demo.Model.Observation.DgnLoinc;
import com.example.demo.Model.Observation.DgnLoincPanel;

import java.util.List;

public interface DgnLoincService {

    public List<DgnLoinc> fetchAllLoincWithPanels();

    public DgnLoincPanel fetchLoincPanel(String loincNum);
}
