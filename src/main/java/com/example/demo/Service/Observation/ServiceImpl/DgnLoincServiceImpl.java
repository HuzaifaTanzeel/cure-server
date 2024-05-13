package com.example.demo.Service.Observation.ServiceImpl;

import com.example.demo.Model.Observation.DgnLoinc;
import com.example.demo.Model.Observation.DgnLoincPanel;
import com.example.demo.Repository.Observation.DgnLoincPanelRepository;
import com.example.demo.Repository.Observation.DgnLoincRepository;
import com.example.demo.Service.Observation.DgnLoincService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgnLoincServiceImpl implements DgnLoincService {


    @Autowired
    private DgnLoincRepository loincRepository;

    @Autowired
    private DgnLoincPanelRepository loincPanelRepository;

    @Transactional
    @Override
    public List<DgnLoinc> fetchAllLoincWithPanels() {
        List<DgnLoinc> loincList = loincRepository.findAll();
        List<DgnLoincPanel> loincPanelList = loincPanelRepository.findAll();

        System.out.println(loincPanelList.size());
        System.out.println(loincList.size());
        for(int i=0;i<loincPanelList.size();i++){
            DgnLoincPanel dgnLoincPanel=loincPanelList.get(i);
            System.out.println(dgnLoincPanel.getLoincNumber());
            if(dgnLoincPanel.getLoincNumber()=="85353-1"){
                System.out.println("Hurrah");
                System.out.println("Hurrah");
            }
        }
        // Form associations where they exist
        for (DgnLoincPanel loincPanel : loincPanelList) {
            DgnLoinc loinc=loincRepository.findByLoincNum(loincPanel.getParentLoincId());
            loinc.addLoincPanel(loincPanel);
        }

        return null;
    }

    @Transactional
    @Override
    public DgnLoincPanel fetchLoincPanel(String loincNum) {

        DgnLoincPanel dgnLoincPanel=loincPanelRepository.findByLoincNumber(loincNum);
        // Form associations where they exist
        return dgnLoincPanel;
    }
}
