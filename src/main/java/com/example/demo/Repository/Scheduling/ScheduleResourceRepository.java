package com.example.demo.Repository.Scheduling;

import com.example.demo.Model.Scheduling.ScheduleResource;
import com.example.demo.Model.Scheduling.ScheduleResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleResourceRepository extends JpaRepository<ScheduleResource, ScheduleResourceId> {
}
