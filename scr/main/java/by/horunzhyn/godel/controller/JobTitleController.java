package by.horunzhyn.godel.controller;

import by.horunzhyn.godel.dto.jobtitle.JobTitleDto;
import by.horunzhyn.godel.dto.jobtitle.JobTitleDtoMapper;
import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.jobtitle.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobTitleController {

    @Autowired
    private JobTitleService service;

    @Autowired
    private JobTitleDtoMapper dtoMapper;

    @GetMapping("/job-titles")
    public List<JobTitleDto> getAll() {
        List<JobTitle> entityList = service.findAll();

        List<JobTitleDto> dtoList = new ArrayList<>();
        for (JobTitle entity : entityList) {
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }

        return dtoList;
    }

    @GetMapping("/job-titles/{id}")
    public JobTitleDto get(@PathVariable("id") Long id) {
        JobTitle entity = service.findOne(id).orElse(null);
        return dtoMapper.mapEntityToDto(entity);
    }

    @PostMapping("/job-titles")
    public JobTitleDto create(@RequestBody JobTitleDto dto) {
        JobTitle savedEntity = service.save(dtoMapper.mapDtoToEntity(dto));
        return dtoMapper.mapEntityToDto(savedEntity);
    }

    @PutMapping("/job-titles/{id}")
    public JobTitleDto update(@PathVariable("id") Long id,
                              @RequestBody JobTitleDto dto) {
        JobTitle updatedEntity = service.update(id, dto.getTitle());
        return dtoMapper.mapEntityToDto(updatedEntity);
    }

    @DeleteMapping("/job-titles/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }


}
