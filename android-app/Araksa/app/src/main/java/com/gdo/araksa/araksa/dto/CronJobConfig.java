package com.gdo.araksa.araksa.dto;

public class CronJobConfig {


    private String timeToStart;
    private String frequency;
    //deactivate for the day
    private boolean isActive;
    //if false deactivate cron job for the day
    private boolean isComing;

    public String getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(String timeToStart) {
        this.timeToStart = timeToStart;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "CronJobConfig{" +
                "timeToStart='" + timeToStart + '\'' +
                ", frequency='" + frequency + '\'' +
                ", isActive=" + isActive +
                ", isComing=" + isComing +
                '}';
    }

    public boolean isComing() {
        return isComing;
    }

    public void setComing(boolean coming) {
        isComing = coming;
    }
}
