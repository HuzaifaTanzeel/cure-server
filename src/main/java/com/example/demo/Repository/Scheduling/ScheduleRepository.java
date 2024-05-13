package com.example.demo.Repository.Scheduling;

import com.example.demo.Model.Scheduling.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

}
