package com.epam.araksa.controllers;

import com.epam.araksa.dto.CronJobConfig;
import org.springframework.web.bind.annotation.GetMapping;

public class AppConfigController {

    @GetMapping(value="/cronjob")
    public CronJobConfig getCronJobConfig(String uuid){
        //response Time to
        return null;
    }

}
