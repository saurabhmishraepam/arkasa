package com.epam.araksa.dto;

import java.sql.Timestamp;

public class EmployeeStatus {

	private String empId;
	private boolean isComing;
	private Timestamp time;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public boolean isComing() {
		return isComing;
	}
	public void setComing(boolean isComing) {
		this.isComing = isComing;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeStatus [empId=");
		builder.append(empId);
		builder.append(", isComing=");
		builder.append(isComing);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}
	
	
}
