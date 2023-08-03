package com.HubertRoszyk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    //TODO solve get method with this relation
    @ManyToOne
    @JoinColumn(name = "activity-type-id")
    @JsonIgnore
    //@JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private ActivityType activityType;

    private String duration;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date date;

    private String time;

    private float distance;
    private float avgSpeed;
    private float maxSpeed;

    private int avgHR;
    private int maxHR;
    private int burnedCalories;

    private String description;
}
