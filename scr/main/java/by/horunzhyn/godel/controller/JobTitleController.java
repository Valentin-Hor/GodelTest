package by.horunzhyn.godel.controller;

import by.horunzhyn.godel.Exceptions.NoSuchEntityFoundException;
import by.horunzhyn.godel.dto.jobtitle.JobTitleDto;
import by.horunzhyn.godel.dto.jobtitle.JobTitleDtoMapper;
import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.jobtitle.JobTitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class JobTitleController {

    private static final Logger logger = LoggerFactory.getLogger(JobTitleController.class);

    @Autowired
    private JobTitleService service;

    @Autowired
    private JobTitleDtoMapper dtoMapper;

    @GetMapping("/job-titles")
    public List<JobTitleDto> getAll() {
        logger.info("Show all job-titles method started");
        List<JobTitle> entityList = service.findAll();

        List<JobTitleDto> dtoList = new ArrayList<>();
        for (JobTitle entity : entityList) {
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }
        logger.info("Job-titles start showed up");
        return dtoList;
    }

    @GetMapping("/job-titles/{id}")
    public JobTitleDto get(@PathVariable("id") Long id) throws NoSuchEntityFoundException  {
        logger.info("Find job-title by id method started");
        JobTitle entity = service.findOne(id).orElseThrow(()->new NoSuchEntityFoundException());
        logger.info("Job-title found");
        return dtoMapper.mapEntityToDto(entity);
    }

    @PostMapping("/job-titles")
    public JobTitleDto create(@RequestBody JobTitleDto dto) {
        logger.info("Create job-title by id method started");
        JobTitle savedEntity = service.save(dtoMapper.mapDtoToEntity(dto));
        logger.info("Job-title created");
        return dtoMapper.mapEntityToDto(savedEntity);
    }

    @PutMapping("/job-titles/{id}")
    public JobTitleDto update(@PathVariable("id") Long id,
                              @RequestBody JobTitleDto dto) {
        logger.info("Update job-title by id method started");
        JobTitle updatedEntity = service.update(id, dto.getTitle());
        logger.info("Job-title updated");
        return dtoMapper.mapEntityToDto(updatedEntity);
    }

    @DeleteMapping("/job-titles/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("Delete job-title by id method started");
        service.delete(id);
        logger.info("Job-title deleted");
    }


}
