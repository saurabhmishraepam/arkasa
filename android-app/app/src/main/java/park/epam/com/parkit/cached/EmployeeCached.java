package park.epam.com.parkit.cached;

import park.epam.com.parkit.dto.EmployeeDetails;

public class EmployeeCached {

    public static EmployeeDetails details=new EmployeeDetails();
    public static boolean isComingToOffice = false;

    public String getEmployeeId(){
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmpId("1234678");
        return employeeDetails.getEmpId();
    }

}
