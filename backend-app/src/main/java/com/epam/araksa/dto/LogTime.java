package com.epam.araksa.dto;

public class LogTime {

    private String empId;
    private String Id;
    private long inTime;
    private long outTime;
    private long startTime;
    private long day;
    private int minutesTaken;
    private ClassifiedWorkingDays cWD;
    private Season season;

    public Season getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return "LogTime{" +
                "empId='" + empId + '\'' +
                ", Id='" + Id + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", startTime=" + startTime +
                ", day=" + day +
                ", minutesTaken=" + minutesTaken +
                ", cWD=" + cWD +
                ", season=" + season +
                ", distanceInKM=" + distanceInKM +
                '}';
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public ClassifiedWorkingDays getcWD() {
        return cWD;
    }

    public void setcWD(ClassifiedWorkingDays cWD) {
        this.cWD = cWD;
    }

    public float getDistanceInKM() {
        return distanceInKM;
    }

    public void setDistanceInKM(float distanceInKM) {
        this.distanceInKM = distanceInKM;
    }

    private float distanceInKM;

    public int getMinutesTaken() {
        return minutesTaken;
    }

    public void setMinutesTaken(int minutesTaken) {
        this.minutesTaken = minutesTaken;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public long getInTime() {
        return inTime;
    }

    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    public long getOutTime() {
        return outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

}
