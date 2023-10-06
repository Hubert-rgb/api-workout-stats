package com.HubertRoszyk.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
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
@Data
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
    private Map<String, String> possibleData;

    @OneToMany(mappedBy = "activityType")
    @JsonBackReference
    private List<Activity> activities;

    public ActivityType(String name, List<String> requiredData, HashMap<String, String> possibleData){
        this.name = name;
        this.requiredData = requiredData;
        this.possibleData = possibleData;
    }
}
