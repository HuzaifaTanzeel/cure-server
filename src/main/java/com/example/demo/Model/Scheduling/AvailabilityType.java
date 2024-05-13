package com.example.demo.Model.Scheduling;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCH_AVAILABILITY_TYPE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABILITY_TYPE_ID")
    private Long availabilityTypeId;

    @Column(name = "AVAILABILITY_TYPE_NAME", length = 40, nullable = false)
    private String availabilityTypeName;

    @Column(name = "BOOKABLE_IND", length = 1, nullable = false)
    private char bookableIndicator;

    @OneToMany(mappedBy = "availabilityType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilitySchedule> availabilityScheduleList=new ArrayList<>();

    public void addAvailabilitySchedule(AvailabilitySchedule availabilitySchedule) {
        availabilityScheduleList.add(availabilitySchedule);
        availabilitySchedule.setAvailabilityType(this);
    }

    // Constructors, getters, and setters
}