package com.example.demo.Repository.Scheduling;

import com.example.demo.Enumerators.Scheduling.DayOfWeek;
import com.example.demo.Model.Scheduling.AvailabilitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleAvailabilityRepository extends JpaRepository<AvailabilitySchedule,Long> {

    List<AvailabilitySchedule> findByResourceId(Long resourceId);

    List<AvailabilitySchedule> findByResourceIdAndWeekDayCode(Long resourceId, DayOfWeek weekDayCode);

    @Query("SELECT a FROM AvailabilitySchedule a JOIN a.schedule s WHERE s.orgPid = :orgId")
    List<AvailabilitySchedule> findByOrgId(Long orgId);

}
