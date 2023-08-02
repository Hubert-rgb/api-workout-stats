package com.HubertRoszyk.service;

import com.HubertRoszyk.entity.Activity;
import com.HubertRoszyk.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository repository;

    public Activity saveActivity(Activity activity){
        return repository.save(activity);
    }
    public List<Activity> getActivitiesByDate(Date date){
        return repository.findAllByDate(date);
    }

    public List<Activity> getAllActivities(){
        return repository.findAll();
    }
}
