package com.HubertRoszyk.controller;

import com.HubertRoszyk.entity.ActivityType;
import com.HubertRoszyk.service.ActivityService;
import com.HubertRoszyk.service.ActivityTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/activityType")
public class ActivityTypeController {
    @Autowired
    ActivityTypeService activityTypeService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<ActivityType> getAllActivitiesType(){
        return activityTypeService.getAllActivityTypes();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ActivityType createActivityType(@RequestBody JSONObject jsonInput){
        String name = (String) jsonInput.get("name");
        List<String> requiredData = (List<String>) jsonInput.get("requiredData");
        HashMap<String, String> possibleData = (HashMap<String, String>) jsonInput.get("possibleData");

        ActivityType activityType = new ActivityType(name, requiredData, possibleData);

        activityTypeService.saveActivityType(activityType);
        return activityType;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{activityTypeId}")
    public void deleteActivityType(@PathVariable Long activityTypeId){
        activityTypeService.deleteActivityTypeById(activityTypeId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{activityTypeId}")
    public ActivityType updateActivityType(@PathVariable Long activityTypeId, @RequestBody JSONObject jsonInput){
        String name = (String) jsonInput.get("name");
        List<String> requiredData = (List<String>) jsonInput.get("requiredData");
        HashMap<String, String> possibleData = (HashMap<String, String>) jsonInput.get("possibleData");

        ActivityType activityType = activityTypeService.getActivityTypeById(activityTypeId);
        activityType.setName(name);
        activityType.setRequiredData(requiredData);
        activityType.setPossibleData(possibleData);

        activityTypeService.saveActivityType(activityType);
        return activityType;
    }
}
