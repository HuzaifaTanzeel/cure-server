package com.example.demo.CommonFramework.Model.Practice;

import com.example.demo.CommonFramework.Model.Facility.GenFacility;
import com.example.demo.CommonFramework.Model.GenParty;
import com.example.demo.Model.Scheduling.Schedule;
import com.example.demo.Model.Scheduling.ScheduleResource;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GEN_ORG")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ORG_ID")
public class GenOrganization extends GenParty {

    @Column(name = "ORG_NAME")
    private String orgName;

    @Column(name = "ORG_TYPE_CD")
    private int orgTypeCd;

    //Org Logo

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenFacility> genFacilities=new ArrayList<>();

    public void addFacility(GenFacility genFacility){
        genFacilities.add(genFacility);
        genFacility.setOrganization(this);
    }

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> scheduleList=new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
        schedule.setOrganization(this);
    }
    public List<GenFacility> getGenFacilities() {
        return genFacilities;
    }

    public void setGenFacilities(List<GenFacility> genFacilities) {
        this.genFacilities = genFacilities;
    }

    public GenOrganization(int partyTypeCd, int orgTypeCd) {
        super(partyTypeCd);
        this.orgTypeCd = orgTypeCd;
    }

    public GenOrganization() {
    }

    public GenOrganization(int partyTypeCd, String orgName, int orgTypeCd) {
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgTypeCd() {
        return orgTypeCd;
    }

    public void setOrgTypeCd(int orgTypeCd) {
        this.orgTypeCd = orgTypeCd;
    }
}
