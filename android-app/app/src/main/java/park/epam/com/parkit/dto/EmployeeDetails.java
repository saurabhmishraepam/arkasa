package park.epam.com.parkit.dto;

public class EmployeeDetails {

    private String empId;
    private String empName;
    private String mobileNumber;
    private Boolean isActive;
    private Long dateRegistered;
    private String jobLevel;
    private String projectTag;
    private String department;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee [");
        builder.append(" empId=");
        builder.append(empId);
        builder.append(", empName=");
        builder.append(empName);
        builder.append(", mobileNumber=");
        builder.append(mobileNumber);
        builder.append(", isActive=");
        builder.append(isActive);
        builder.append(", dateRegistered=");
        builder.append(dateRegistered);
        builder.append("]");
        return builder.toString();
    }
}
