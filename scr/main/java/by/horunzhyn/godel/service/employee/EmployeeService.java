package by.horunzhyn.godel.service.employee;

import by.horunzhyn.godel.dao.EmployeeRepository;
import by.horunzhyn.godel.data.Gender;
import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.entity.Employee;
import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.BaseServiceImpl;
import by.horunzhyn.godel.service.department.DepartmentService;
import by.horunzhyn.godel.service.jobtitle.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeService extends BaseServiceImpl<Employee,EmployeeRepository> {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobTitleService jobTitleService;

    @Autowired
    private EmployeeRepository EmployeeRepository;

    @Override
    protected EmployeeRepository getRepository(){
        return EmployeeRepository;
    }

    public Employee create(Employee entity, Long departmentId, Long jobTitleId) {
        Department department = departmentService.findOne(departmentId).orElse(null);
        entity.setDepartment(department);

        JobTitle jobTitle = jobTitleService.findOne(jobTitleId).orElse(null);
        entity.setJobTitle(jobTitle);

        return save(entity);
    }

    public Employee update(Long id,
                           String firstName,
                           String lastName,
                           Gender gender,
                           LocalDate dateOfBirth,
                           Long departmentId,
                           Long jobTitleId) {
        Optional<Employee> optional = findOne(id);

        if (!optional.isPresent()) {
            return null;
        }

        Employee entity = optional.get();
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setGender(gender);
        entity.setDateOfBirth(dateOfBirth);

        Department department = departmentService.findOne(departmentId).orElse(null);
        entity.setDepartment(department);

        JobTitle jobTitle = jobTitleService.findOne(jobTitleId).orElse(null);
        entity.setJobTitle(jobTitle);

        return save(entity);
    }
}
