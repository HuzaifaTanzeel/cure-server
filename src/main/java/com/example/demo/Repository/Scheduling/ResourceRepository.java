package com.example.demo.Repository.Scheduling;

import com.example.demo.Model.Scheduling.SchedulableResource;
import com.example.demo.Model.Scheduling.ScheduleResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<SchedulableResource,Long> {
}
