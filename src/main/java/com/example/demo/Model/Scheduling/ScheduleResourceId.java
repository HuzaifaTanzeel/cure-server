package com.example.demo.Model.Scheduling;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResourceId implements Serializable {

    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "RESOURCE_ID")
    private Long resourceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleResourceId that = (ScheduleResourceId) o;
        return Objects.equals(scheduleId, that.scheduleId) && Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, resourceId);
    }
// Equals and hashcode methods
}