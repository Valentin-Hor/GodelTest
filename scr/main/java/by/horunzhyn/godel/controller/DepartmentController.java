package by.horunzhyn.godel.controller;

import by.horunzhyn.godel.dto.department.DepartmentDto;
import by.horunzhyn.godel.dto.department.DepartmentDtoMapper;
import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.service.department.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService service;

    @Autowired
    private DepartmentDtoMapper dtoMapper;

    @GetMapping("/departments")
    public List<DepartmentDto> getAll() {
        logger.info("Show all departments method started");

        List<Department> entityList = service.findAll();

        List<DepartmentDto> dtoList = new ArrayList<>();
        for (Department entity : entityList) {
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }
        logger.info("Departments start showed up");
        return dtoList;
    }

    @GetMapping("/departments/{id}")
    public DepartmentDto get(@PathVariable("id") Long id){
        logger.info("Find department by id method started");

        Department entity = service.findOne(id).orElse(null);
        logger.info("Department found");
        return dtoMapper.mapEntityToDto(entity);
    }

    @PostMapping("/departments")
    public DepartmentDto create(@RequestBody DepartmentDto dto){
        logger.info("Create department method started");
        Department savedEntity = service.save(dtoMapper.mapDtoToEntity(dto));

        logger.info("Department created");
        return dtoMapper.mapEntityToDto(savedEntity);
    }

    @PutMapping("/departments/{id}")
    public DepartmentDto update(@PathVariable("id") Long id,
                                @RequestBody DepartmentDto dto){
        logger.info("Update department by id method started");
        Department updatedEntity = service.update(id,dto.getTitle());
        logger.info("Department updated");
        return dtoMapper.mapEntityToDto(updatedEntity);
    }

    @DeleteMapping("/departments/{id}")
    public void delete(@PathVariable("id") Long id){
        logger.info("Delete department by id method started");
        service.delete(id);
        logger.info("Department deleted");
    }















}
