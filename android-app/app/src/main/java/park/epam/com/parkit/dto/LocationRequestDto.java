package park.epam.com.parkit.dto;

public class LocationRequestDto {
    private String id;
    private String empId;
    private double lat;
    private double lang;
    private long lastUpdated;
    private int updateCount;
    private Boolean isMovingToOffice;
    private long timeToReachOffice;
    private long currentDistanceInKms;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "LocationRequestDto{" +
                "id='" + id + '\'' +
                ", empId='" + empId + '\'' +
                ", lat=" + lat +
                ", lang=" + lang +
                ", lastUpdated=" + lastUpdated +
                ", updateCount=" + updateCount +
                ", isMovingToOffice=" + isMovingToOffice +
                ", timeToReachOffice=" + timeToReachOffice +
                ", currentDistanceInKms=" + currentDistanceInKms +
                ", isComing=" + isComing +
                '}';
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public boolean isComing() {
        return isComing;
    }

    public void setComing(boolean coming) {
        isComing = coming;
    }

    private boolean isComing;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeToReachOffice() {
        return timeToReachOffice;
    }

    public void setTimeToReachOffice(long timeToReachOffice) {
        this.timeToReachOffice = timeToReachOffice;
    }

    public long getDistanceCurrentInKms() {
        return currentDistanceInKms;
    }

    public void setDistanceCurrentInKms(long distanceCurrentInKms) {
        this.currentDistanceInKms = distanceCurrentInKms;
    }
    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public Boolean getMovingToOffice() {
        return isMovingToOffice;
    }

    public void setMovingToOffice(Boolean movingToOffice) {
        isMovingToOffice = movingToOffice;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public long getCurrentDistanceInKms() {
        return currentDistanceInKms;
    }

    public void setCurrentDistanceInKms(long currentDistanceInKms) {
        this.currentDistanceInKms = currentDistanceInKms;
    }


}
