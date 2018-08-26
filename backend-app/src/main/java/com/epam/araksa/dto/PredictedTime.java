package com.epam.araksa.dto;

public class PredictedTime {

    private long empId;
    private float timetoTake;
    private String forDate;

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public float getTimetoTake() {
        return timetoTake;
    }

    public void setTimetoTake(float timetoTake) {
        this.timetoTake = timetoTake;
    }

    public String getForDate() {
        return forDate;
    }

    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

    @Override
    public String toString() {
        return "PredictedTime{" +
                "empId=" + empId +
                ", timetoTake=" + timetoTake +
                ", forDate=" + forDate +
                '}';
    }
}
