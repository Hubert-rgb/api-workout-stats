package com.HubertRoszyk.controller;

import com.HubertRoszyk.entity.Activity;
import com.HubertRoszyk.service.ActivityService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @PostMapping
    @CrossOrigin(origins = "*")
    public String createActivity(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            Activity activity = objectMapper.readValue(jsonObject.toJSONString(), Activity.class);
            activityService.saveActivity(activity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "activity-added";
    }
    @GetMapping("/{date}")
    @CrossOrigin(origins = "*")
    public List<Activity> getAllActivityByDate(@RequestParam Date date){
        return activityService.getActivitiesByDate(date);
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Activity> getAllActivity(){
        return activityService.getAllActivities();
    }
}
