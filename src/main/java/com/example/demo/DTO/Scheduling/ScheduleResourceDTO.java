package com.example.demo.DTO.Scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResourceDTO {
    private Long scheduleId;
    private Long facilityId;
    private Long practitionerPid;
    private Long partyRoleTypeId;
}

