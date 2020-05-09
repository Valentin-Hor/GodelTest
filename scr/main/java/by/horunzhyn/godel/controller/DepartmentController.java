package by.horunzhyn.godel.controller;

import by.horunzhyn.godel.dto.department.DepartmentDto;
import by.horunzhyn.godel.dto.department.DepartmentDtoMapper;
import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @Autowired
    private DepartmentDtoMapper dtoMapper;

    @GetMapping("/departments")
    public List<DepartmentDto> getAll() {
        List<Department> entityList = service.findAll();

        List<DepartmentDto> dtoList = new ArrayList<>();
        for (Department entity : entityList) {
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }

        return dtoList;
    }

    @GetMapping("/departments/{id}")
    public DepartmentDto get(@PathVariable("id") Long id){
        Department entity = service.findOne(id).orElse(null);
        return dtoMapper.mapEntityToDto(entity);
    }

    @PostMapping("/departments")
    public DepartmentDto create(@RequestBody DepartmentDto dto){
        Department savedEntity = service.save(dtoMapper.mapDtoToEntity(dto));
        return dtoMapper.mapEntityToDto(savedEntity);
    }

    @PutMapping("/departments/{id}")
    public DepartmentDto update(@PathVariable("id") Long id,
                                @RequestBody DepartmentDto dto){
        Department updatedEntity = service.update(id,dto.getTitle());
        return dtoMapper.mapEntityToDto(updatedEntity);
    }

    @DeleteMapping("/departments/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }















}
