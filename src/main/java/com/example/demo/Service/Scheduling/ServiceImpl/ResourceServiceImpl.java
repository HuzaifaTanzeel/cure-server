package com.example.demo.Service.Scheduling.ServiceImpl;

import com.example.demo.Enumerators.Scheduling.ResourceType;
import com.example.demo.Model.Scheduling.SchedulableResource;
import com.example.demo.Repository.Scheduling.ResourceRepository;
import com.example.demo.Service.Scheduling.ResourceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;


    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional
    @Override
    public SchedulableResource createPracFascilityResource() {

        SchedulableResource schedulableResource = new SchedulableResource();
        schedulableResource.setResourceTypeCode(ResourceType.PRACT_FAC);

        return resourceRepository.save(schedulableResource);
    }
}
