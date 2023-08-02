package com.HubertRoszyk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Jacksonized
public class ActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty
    private String name;
    @JsonProperty
    private List<String> requiredData;
    @JsonProperty
    @ElementCollection
    private Map<String, String> possibleData = new HashMap<>();

    @OneToMany(mappedBy = "activityType")
    private List<Activity> activities;

    public ActivityType(String name, List<String> requiredData, HashMap<String, String> possibleData){
        this.name = name;
        this.requiredData = requiredData;
        this.possibleData = possibleData;
    }
}
