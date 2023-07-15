package com.HubertRoszyk.entity;

import com.HubertRoszyk.enumTypes.ActivityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    private ActivityType activityType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date duration;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date time;

    private float distance;
    private float avgSpeed;
    private float maxSpeed;

    private int avgHR;
    private int maxHR;
    private int burnedCalories;

    private String description;
}
