package com.example.demo.DTO.Scheduling;

import com.example.demo.Enumerators.Scheduling.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityScheduleDTO {
    private Long availabilityId;
    private Long resourceId;
    private Long scheduleId;
    private DayOfWeek weekDayCode;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long availabilityTypeId;
    private int numberOfSlots;
    private int slotDuration;
}
