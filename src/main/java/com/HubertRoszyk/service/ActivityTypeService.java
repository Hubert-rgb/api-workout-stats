package com.HubertRoszyk.service;

import com.HubertRoszyk.entity.ActivityType;
import com.HubertRoszyk.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeService {
    @Autowired
    ActivityTypeRepository repository;

    public List<ActivityType> getAllActivityTypes(){
        return repository.findAll();
    }

    public ActivityType saveActivityType(ActivityType activityType){
        return repository.save(activityType);
    }

    public ActivityType getActivityTypeById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void deleteActivityTypeById(Long id) {
        repository.deleteById(id);
    }
}
