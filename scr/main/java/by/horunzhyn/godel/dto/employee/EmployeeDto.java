package by.horunzhyn.godel.dto.employee;

import by.horunzhyn.godel.data.Gender;
import by.horunzhyn.godel.dto.department.DepartmentDto;
import by.horunzhyn.godel.dto.jobtitle.JobTitleDto;

import java.time.LocalDate;

public class EmployeeDto extends BaseEmployeeDto {

    private DepartmentDto department;
    private JobTitleDto jobTitle;

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public JobTitleDto getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleDto jobTitle) {
        this.jobTitle = jobTitle;
    }
}
