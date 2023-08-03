package com.HubertRoszyk.controller;

import com.HubertRoszyk.entity.Activity;
import com.HubertRoszyk.service.ActivityService;
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

    @PostMapping
    @CrossOrigin(origins = "*")
    public String createActivity(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {

            Activity activity = objectMapper.readValue(jsonObject.toJSONString(), Activity.class);
            activity.setDate(getDateFromJSON(jsonObject));
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
        List<Activity> activities = activityService.getAllActivities();
        System.out.println(activities.get(0).getDate());
        return activityService.getAllActivities();
    }
    private LocalDateTime getDateFromJSON(JSONObject jsonObject){
        LocalDateTime date = null;
        try{
            Long dateMs = (Long) jsonObject.get("date");
            String time = (String) jsonObject.get("time");
            List<String> timeArr = List.of(time.split(":"));
            int timeHour = Integer.parseInt(timeArr.get(0));
            int timeMinutes = Integer.parseInt(timeArr.get(1));

            date = Instant.ofEpochMilli(dateMs).atZone(ZoneId.systemDefault()).toLocalDateTime();
            date = date.withHour(timeHour);
            date = date.withMinute(timeMinutes);
            return date;
        } catch (NullPointerException exception) {
            System.out.println(exception);
            return date;
        }
    }
}
