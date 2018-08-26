package park.epam.com.parkit.dto;

public class DashboardDto {
    private int floatersAvailable;
    private int dedicatedSlots;
    private int floaterSlots;
    private int availableSlots;
    private int occupiedSlots;
    private int myRank;
    private long lastUpdated;
    private int onTheWay;

    public int getDedicatedSlots() {
        return dedicatedSlots;
    }

    public void setDedicatedSlots(int dedicatedSlots) {
        this.dedicatedSlots = dedicatedSlots;
    }

    public int getFloaterSlots() {
        return floaterSlots;
    }

    public void setFloaterSlots(int floaterSlots) {
        this.floaterSlots = floaterSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public void setOccupiedSlots(int occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public int getMyRank() {
        return myRank;
    }

    public void setMyRank(int myRank) {
        this.myRank = myRank;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getFloatersAvailable() {
        return floatersAvailable;
    }

    public int getOnTheWay() {
        return onTheWay;
    }

    public void setOnTheWay(int onTheWay) {
        this.onTheWay = onTheWay;
    }

    public void setFloatersAvailable(int floatersAvailable) {
        this.floatersAvailable = floatersAvailable;
    }

    @Override
    public String toString() {
        return "DashboardDto{" +
                "floatersAvailable=" + floatersAvailable +
                ", dedicatedSlots=" + dedicatedSlots +
                ", floaterSlots=" + floaterSlots +
                ", availableSlots=" + availableSlots +
                ", occupiedSlots=" + occupiedSlots +
                ", myRank=" + myRank +
                ", lastUpdated=" + lastUpdated +
                ", onTheWay=" + onTheWay +
                '}';
    }
}
