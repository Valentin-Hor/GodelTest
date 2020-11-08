package by.horunzhyn.godel;

import by.horunzhyn.godel.dao.EmployeeRepository;
import by.horunzhyn.godel.data.Gender;
import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.entity.Employee;
import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.employee.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindById() {

        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstName("testFirstName");
        employee.setLastName("testLastName");

        doReturn(Optional.of(employee)).when(employeeRepository).findById(1l);

        Optional<Employee> returnedEmployee = employeeService.findOne(1l);

        Assertions.assertTrue(returnedEmployee.isPresent(), "Employee was found");
        Assertions.assertSame(returnedEmployee.get(), employee, "Returned and the simple created object are not equal");
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("testFirstName1");
        employee1.setLastName("testLastName1");
        employee1.setId(1l);
        employee1.setGender(Gender.MALE);
        Employee employee2 = new Employee();
        employee2.setId(2l);
        employee2.setFirstName("testFirstName2");
        employee2.setLastName("testLastName2");
        employee2.setGender(Gender.FEMALE);

        doReturn(Arrays.asList(employee1, employee2)).when(employeeRepository).findAll();
        List<Employee> employees = employeeService.findAll();
        Assertions.assertEquals(2, employees.size(), "Method FindAll should returned 2 employees");
    }

    @Test
    public void testSaveEmployee() {
        Department department = new Department("testDepartment");
        department.setId(1l);
        JobTitle jobTitle = new JobTitle("testJobTitle");
        jobTitle.setId(1l);
        Employee employee = new Employee("firstName","lastName",
                department,jobTitle,Gender.MALE,LocalDate.of(1980,06,06));
        employee.setId(1l);


        doReturn(employee).when(employeeRepository).saveAndFlush(employee);

        Employee savedEmployee = employeeService.save(employee);

        Assertions.assertNotNull(savedEmployee, "Saved Employee should not be Null");
        Assertions.assertEquals(savedEmployee,employee);

    }

    @Test
    public void testFindByIdAndNotFound() {
        doReturn(Optional.empty()).when(employeeRepository).findById(1l);
        Optional<Employee> returnedEmployee = employeeService.findOne(1l);

        Assertions.assertFalse(returnedEmployee.isPresent(), "Employee should not be Found");
    }


}
