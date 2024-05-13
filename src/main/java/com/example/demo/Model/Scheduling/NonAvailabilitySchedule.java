package com.example.demo.Model.Scheduling;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SCH_SCH_NON_AVAILABILITY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonAvailabilitySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NON_AVAILABILITY_ID")
    private Long nonAvailabilityId;

    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "START_PERIOD_DATE_TM")
    private LocalDateTime startPeriodDateTime;

    @Column(name = "END_PERIOD_DT_TM")
    private LocalDateTime endPeriodDateTime;

    @Column(name = "REASON", length = 255)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "SCHEDULE_ID",insertable = false, updatable = false)
    private Schedule schedule;
}
