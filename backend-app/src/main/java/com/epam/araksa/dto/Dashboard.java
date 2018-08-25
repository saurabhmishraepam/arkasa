/**
 * 
 */
package com.epam.araksa.dto;

/**
 * @author Rakesh_Gupta
 *
 */
public class Dashboard {

	private int dedicatedSlots;
	private int floaterSlots;
	private int availableSlots;
	private int occupiedSlots;
	private int myRank;
	private long lastUpdated;

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

	@Override
	public String toString() {
		return "Dashboard{" +
				"dedicatedSlots=" + dedicatedSlots +
				", floaterSlots=" + floaterSlots +
				", availableSlots=" + availableSlots +
				", occupiedSlots=" + occupiedSlots +
				", myRank=" + myRank +
				", lastUpdated='" + lastUpdated + '\'' +
				'}';
	}
}