package com.example.demo.CommonFramework.Services.Implementation;

import com.example.demo.CommonFramework.Repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {



    @Autowired
    private final OrganizationRepository organizationRepository;

    public OrgService( OrganizationRepository organizationRepository) {

        this.organizationRepository = organizationRepository;
    }

}
