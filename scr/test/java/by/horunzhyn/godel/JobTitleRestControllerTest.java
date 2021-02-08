package by.horunzhyn.godel;


import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.jobtitle.JobTitleService;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobTitleRestControllerTest {

    @MockBean
    private JobTitleService jobTitleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllJobTitle() throws Exception {
        JobTitle jobTitle1 = new JobTitle("firstJobTitle");
        jobTitle1.setId(1L);
        JobTitle jobTitle2 = new JobTitle("secondJobTitle");
        jobTitle2.setId(2L);

        doReturn(Lists.newArrayList(jobTitle1, jobTitle2)).when(jobTitleService).findAll();

        mockMvc.perform(get("/job-titles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("firstJobTitle")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("secondJobTitle")));

    }

    @Test
    void testGetJobTitleById() throws Exception {

        JobTitle jobTitle = new JobTitle("testJobTitle");
        jobTitle.setId(1L);

        doReturn(Optional.of(jobTitle)).when(jobTitleService).findOne(1L);

        mockMvc.perform(get("/job-titles/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testJobTitle")));
    }

    @Test
    void testGetJobTitleByIdNotFound() throws Exception{

        doReturn(Optional.empty()).when(jobTitleService).findOne(1L);

        mockMvc.perform(get("/job-titles/{id}", 1))
                .andExpect(status().isNotFound());

    }

    @Test
    void testCreateJobTitle() throws Exception{

        JobTitle jobTitleToPost = new JobTitle("testJobTitle");
        JobTitle jobTitleToReturn = new JobTitle("testJobTitle");
        jobTitleToReturn.setId(1L);

        doReturn(jobTitleToReturn).when(jobTitleService).save(any());

        mockMvc.perform(post("/job-titles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(jobTitleToPost)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testJobTitle")));

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
