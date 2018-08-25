package com.epam.araksa.dto;

import java.sql.Timestamp;

public class EmployeeStatus {

	private String empId;
	private Boolean isComing;
	private long time;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Boolean isComing() {
		return isComing;
	}
	public void setisComing(Boolean isComing) {
		this.isComing = isComing;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
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
