package com.example.demo.Service.Scheduling.ServiceImpl;

import com.example.demo.Model.Scheduling.Schedule;
import com.example.demo.Repository.Scheduling.ScheduleRepository;
import com.example.demo.Service.Scheduling.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    @Override
    public Schedule createSchedule(Long orgId) {

        LocalDate currentDate = LocalDate.now();

        Schedule schedule = new Schedule();
        schedule.setName("My First Schedule");
        schedule.setEffectiveStartDate(currentDate);
        schedule.setOrgPid(orgId);
        return scheduleRepository.save(schedule);
    }
}
