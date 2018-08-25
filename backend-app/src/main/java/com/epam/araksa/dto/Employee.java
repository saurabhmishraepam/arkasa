package com.epam.araksa.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "emp_details")
public class Employee {

	private String empId;
	private String empName;
	private String mobileNumber;
	private ParkType parkType;
	private Boolean isActive;
	private Long dateRegistered;
	private String jobLevel;
	private String projectTag;
	private JobFunction jFun;
	private String department;
	private String email;

	public JobFunction getjFun() {
		return jFun;
	}

	public void setjFun(JobFunction jFun) {
		this.jFun = jFun;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getProjectTag() {
		return projectTag;
	}

	public void setProjectTag(String projectTag) {
		this.projectTag = projectTag;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public ParkType getParkType() {
		return parkType;
	}

	public void setParkType(ParkType parkType) {
		this.parkType = parkType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Long dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empId=");
		builder.append(empId);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", parkType=");
		builder.append(parkType);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", dateRegistered=");
		builder.append(dateRegistered);
		builder.append(", jobLevel=");
		builder.append(jobLevel);
		builder.append(", projectTag=");
		builder.append(projectTag);
		builder.append(", jFun=");
		builder.append(jFun);
		builder.append(", department=");
		builder.append(department);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

	 
	 
}