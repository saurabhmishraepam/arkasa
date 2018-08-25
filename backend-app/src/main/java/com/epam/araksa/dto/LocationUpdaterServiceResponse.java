package com.epam.araksa.dto;

public class LocationUpdaterServiceResponse {
    private String uuid;
    private boolean isMovingTowardsOffice;
    private int recheckcounter;
    private boolean isActive;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isMovingTowardsOffice() {
        return isMovingTowardsOffice;
    }

    public void setMovingTowardsOffice(boolean movingTowardsOffice) {
        isMovingTowardsOffice = movingTowardsOffice;
    }

    public int getRecheckcounter() {
        return recheckcounter;
    }

    public void setRecheckcounter(int recheckcounter) {
        this.recheckcounter = recheckcounter;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "LocationUpdaterServiceResponse{" +
                "uuid='" + uuid + '\'' +
                ", isMovingTowardsOffice=" + isMovingTowardsOffice +
                ", recheckcounter=" + recheckcounter +
                ", isActive=" + isActive +
                '}';
    }
}
