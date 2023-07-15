package com.HubertRoszyk.controller;

import com.HubertRoszyk.enumTypes.ActivityType;
import com.HubertRoszyk.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @CrossOrigin(origins = "*")
    @GetMapping("/activityType")
    public List<Enum> getAllActivitiesType(){
        List<Enum> activitiesTypesEnumValues = new ArrayList<>(EnumSet.allOf(ActivityType.class));
        return activitiesTypesEnumValues;
    }
}
