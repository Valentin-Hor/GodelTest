package by.horunzhyn.godel;

import by.horunzhyn.godel.data.Gender;
import by.horunzhyn.godel.dto.department.DepartmentDto;
import by.horunzhyn.godel.dto.employee.EmployeeDto;
import by.horunzhyn.godel.dto.jobtitle.JobTitleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
public class JsonSerializationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSerialization() throws Exception{
        JobTitleDto jobTitleDto = new JobTitleDto();
        jobTitleDto.setId(1l);
        jobTitleDto.setTitle("testJobTitle");
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1l);
        departmentDto.setTitle("testDepartment");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1l);
        employeeDto.setFirstName("firstName");
        employeeDto.setLastName("lastName");
        employeeDto.setGender(Gender.MALE);
        employeeDto.setDateOfBirth(LocalDate.of(1980,06,06));
        employeeDto.setJobTitle(jobTitleDto);
        employeeDto.setDepartment(departmentDto);

        String json = objectMapper.writeValueAsString(employeeDto);

        assertEquals("{\"id\":1,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"gender\":\"MALE\",\"dateOfBirth\":\"1980-06-06\"," +
                "\"department\":{\"id\":1,\"title\":\"testDepartment\"},\"jobTitle\":{\"id\":1,\"title\":\"testJobTitle\"}}",json);

    }

    @Test
    public void testDeserialization() throws Exception{
        String json ="{\"id\":1,\"firstName\":\"Valentin\",\"lastName\":\"Khorunzhyn\",\"gender\":\"MALE\",\"dateOfBirth\":\"1987-02-04\"," +
                "\"department\":{\"id\":1,\"title\":\"Administration\"},\"jobTitle\":{\"id\":1,\"title\":\"Director\"}}";
        EmployeeDto employeeDto = objectMapper.readValue(json,EmployeeDto.class);
        assertEquals(1l,employeeDto.getId());
        assertEquals("Valentin",employeeDto.getFirstName());
        assertEquals("Khorunzhyn",employeeDto.getLastName());
        assertEquals(Gender.MALE,employeeDto.getGender());
        assertEquals(LocalDate.of(1987,02,04),employeeDto.getDateOfBirth());
        assertEquals("Administration",employeeDto.getDepartment().getTitle());
        assertEquals("Director",employeeDto.getJobTitle().getTitle());
    }
}
