package by.horunzhyn.godel;

import by.horunzhyn.godel.data.Gender;
import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.entity.Employee;
import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.employee.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
 class EmployeeRestControllerTest {

    @MockBean
    private EmployeeService employeeService;


    @Autowired
    private MockMvc mockMvc;


    @Test
     void testGetAllEmployee() throws Exception {
        Department department = new Department("testDepartment");
        JobTitle jobTitle = new JobTitle("testJobTitle");
        Employee employee1 = new Employee("firstName", "lastName", department, jobTitle, Gender.MALE,
                LocalDate.of(1980, 6, 6));
        employee1.setId(1L);
        Employee employee2 = new Employee("firstName2", "lastName2", department, jobTitle, Gender.FEMALE,
                LocalDate.of(1981, 7, 7));
        employee2.setId(2L);

        doReturn(Lists.newArrayList(employee1, employee2)).when(employeeService).findAll();

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("firstName")))
                .andExpect(jsonPath("$[0].lastName", is("lastName")))
                .andExpect(jsonPath("$[0].dateOfBirth", is("1980-06-06")))
                .andExpect(jsonPath("$[0].gender", is("MALE")))
                .andExpect(jsonPath("$[0].department.title", is("testDepartment")))
                .andExpect(jsonPath("$[0].jobTitle.title", is("testJobTitle")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("firstName2")))
                .andExpect(jsonPath("$[1].lastName", is("lastName2")))
                .andExpect(jsonPath("$[1].dateOfBirth", is("1981-07-07")))
                .andExpect(jsonPath("$[1].gender", is("FEMALE")))
                .andExpect(jsonPath("$[1].department.title", is("testDepartment")))
                .andExpect(jsonPath("$[1].jobTitle.title", is("testJobTitle")));
    }

    @Test
     void testGetEmployeeById() throws Exception {
        Department department = new Department("testDepartment");
        JobTitle jobTitle = new JobTitle("testJobTitle");
        Employee employee = new Employee("firstName", "lastName", department, jobTitle, Gender.MALE,
                LocalDate.of(1980, 6, 6));
        employee.setId(1L);

        doReturn(Optional.of(employee)).when(employeeService).findOne(1L);

        mockMvc.perform(get("/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("firstName")))
                .andExpect(jsonPath("$.lastName", is("lastName")))
                .andExpect(jsonPath("$.dateOfBirth", is("1980-06-06")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.department.title", is("testDepartment")))
                .andExpect(jsonPath("$.jobTitle.title", is("testJobTitle")));
    }

    @Test
     void testGetEmployeeByIdNotFound() throws Exception{
        doReturn(Optional.empty()).when(employeeService).findOne(1L);

        mockMvc.perform(get("/employees/{id}", 1))
                .andExpect(status().isNotFound());

    }

    @Test
     void testCreateEmployee() throws Exception{
        Department department = new Department("testDepartment");
        department.setId(1L);
        JobTitle jobTitle = new JobTitle("testJobTitle");
        jobTitle.setId(1L);

        Employee employeeToPost = new Employee("firstName","lastName",department,jobTitle,
                Gender.MALE,LocalDate.of(1980,6,6));
        Employee employeeToReturn = new Employee("firstName", "lastName", department, jobTitle, Gender.MALE,
                LocalDate.of(1980, 6, 6));
        employeeToReturn.setId(1L);
        Employee employee = new Employee();
        employee.setFirstName("firstName");
        employee.setLastName("lastName");

        doReturn(employee).when(employeeService).save(any());

        mockMvc.perform(post("/employees")
        .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee)))
//        .content("{\n" +
//                "    \"firstName\": \"firstName\",\n" +
//                "    \"lastName\": \"lastName\",\n" +
//                "    \"gender\": \"MALE\",\n" +
//                "    \"dateOfBirth\": \"1980-06-06\",\n" +
//                "    \"departmentId\":1,\n" +
//                "    \"jobTitleId\":1\n" +
//                "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("firstName")))
                .andExpect(jsonPath("$.lastName", is("lastName")))
                .andExpect(jsonPath("$.dateOfBirth", is("1980-06-06")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.department.title", is("testDepartment")))
                .andExpect(jsonPath("$.jobTitle.title", is("testJobTitle")));
    }

     private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
