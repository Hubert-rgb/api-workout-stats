package com.HubertRoszyk.controller;

import com.HubertRoszyk.entity.Activity;
import com.HubertRoszyk.entity.ActivityType;
import com.HubertRoszyk.service.ActivityService;
import com.HubertRoszyk.service.ActivityTypeService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityTypeService activityTypeService;

    @PostMapping
    @CrossOrigin(origins = "*")
    public String createActivity(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            Long dateMs = (Long) jsonObject.get("date");
            Long activityTypeId = Long.valueOf((Integer) jsonObject.get("activityTypeId"));
            ActivityType activityType = activityTypeService.getActivityTypeById(activityTypeId);

            Date date = new Date(dateMs);
            Activity activity = objectMapper.readValue(jsonObject.toJSONString(), Activity.class);

            activity.setDate(date);
            activity.setActivityType(activityType);
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
    public List<Activity> getAllActivity() {
        return activityService.getAllActivities();
    }
}
