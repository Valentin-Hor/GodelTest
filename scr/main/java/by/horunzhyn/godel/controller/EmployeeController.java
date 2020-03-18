package by.horunzhyn.godel.controller;

import by.horunzhyn.godel.dto.employee.EmployeeDto;
import by.horunzhyn.godel.dto.employee.EmployeeDtoMapper;
import by.horunzhyn.godel.dto.employee.PersistEmployeeDto;
import by.horunzhyn.godel.entity.Employee;
import by.horunzhyn.godel.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeDtoMapper dtoMapper;

    @GetMapping("/employees")
    public List<EmployeeDto> getAll() {
        List<Employee> entityList = service.findAll();

        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee entity : entityList) {
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }

        return dtoList;
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto get(@PathVariable("id") Long id) {
        Employee entity = service.findOne(id).orElse(null);
        return dtoMapper.mapEntityToDto(entity);
    }

    @PostMapping("/employees")
    public EmployeeDto create(@RequestBody PersistEmployeeDto dto) {
        Employee savedEntity = service.create(dtoMapper.mapPersistDtoToEntity(dto), dto.getDepartmentId(), dto.getJobTitleId());
        return dtoMapper.mapEntityToDto(savedEntity);
    }

    @PutMapping("/employees/{id}")
    public EmployeeDto update(@PathVariable("id") Long id,
                              @RequestBody PersistEmployeeDto dto) {
        Employee updatedEntity = service.update(id, dto.getFirstName(), dto.getLastName(), dto.getGender(), dto.getDateOfBirth(), dto.getDepartmentId(), dto.getJobTitleId());
        return dtoMapper.mapEntityToDto(updatedEntity);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }


}
