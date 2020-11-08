package by.horunzhyn.godel.controller;

import by.horunzhyn.godel.Exceptions.NoSuchEntityFoundException;
import by.horunzhyn.godel.dto.employee.EmployeeDto;
import by.horunzhyn.godel.dto.employee.EmployeeDtoMapper;
import by.horunzhyn.godel.dto.employee.PersistEmployeeDto;
import by.horunzhyn.godel.entity.Employee;
import by.horunzhyn.godel.service.employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeDtoMapper dtoMapper;

    @GetMapping("/employees")
    public List<EmployeeDto> getAll() {
        logger.info("Show all employees method started");
        List<Employee> entityList = service.findAll();

        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee entity : entityList) {
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }
        logger.info("Employees start showed up");
        return dtoList;
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto get(@PathVariable("id") Long id) throws NoSuchEntityFoundException {
        logger.info("Show employee by id method started");
        Employee entity = service.findOne(id).orElseThrow(() -> new NoSuchEntityFoundException());
        logger.info("Employee found");
        return dtoMapper.mapEntityToDto(entity);
    }

    @PostMapping("/employees")
    public EmployeeDto create(@RequestBody PersistEmployeeDto dto) {
        logger.info("Create employee method started");
        Employee savedEntity = service.create(dtoMapper.mapPersistDtoToEntity(dto), dto.getDepartmentId(), dto.getJobTitleId());
        logger.info("Employee created");
        return dtoMapper.mapEntityToDto(savedEntity);
    }

    @PutMapping("/employees/{id}")
    public EmployeeDto update(@PathVariable("id") Long id,
                              @RequestBody PersistEmployeeDto dto) {
        logger.info("Update employee method started");
        Employee updatedEntity = service.update(id, dto.getFirstName(), dto.getLastName(), dto.getGender(), dto.getDateOfBirth(), dto.getDepartmentId(), dto.getJobTitleId());
        logger.info("Employee updated");
        return dtoMapper.mapEntityToDto(updatedEntity);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("Delete employee by id method started");
        service.delete(id);
        logger.info("Employee deleted");
    }


}
