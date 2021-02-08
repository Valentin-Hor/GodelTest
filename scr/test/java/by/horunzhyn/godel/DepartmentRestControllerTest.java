package by.horunzhyn.godel;


import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.service.department.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentRestControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllDepartments() throws Exception {

        Department department1 = new Department("firstDepartment");
        department1.setId(1L);
        Department department2 = new Department("secondDepartment");
        department2.setId(2L);

        doReturn(Lists.newArrayList(department1, department2)).when(departmentService).findAll();

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("firstDepartment")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("secondDepartment")));

    }

    @Test
    void testGetDepartmentById() throws Exception {

        Department department = new Department("testDepartment");
        department.setId(1L);

        doReturn(Optional.of(department)).when(departmentService).findOne(1L);

        mockMvc.perform(get("/departments/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testDepartment")));

    }

    @Test
    void testGetDepartmentByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(departmentService).findOne(1L);

        mockMvc.perform(get("/departments/{id}", 1))
                .andExpect(status().isNotFound());

    }


    @Test
    void testCreateDepartment() throws Exception {

        Department departmentToPost = new Department("testDepartment");
        Department departmentToReturn = new Department("testDepartment");
        departmentToReturn.setId(1L);

        doReturn(departmentToReturn).when(departmentService).save(any());

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(departmentToPost)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testDepartment")));


    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
