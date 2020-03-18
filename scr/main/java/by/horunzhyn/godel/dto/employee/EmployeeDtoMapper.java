package by.horunzhyn.godel.dto.employee;

import by.horunzhyn.godel.dto.BaseDtoMapper;
import by.horunzhyn.godel.dto.department.DepartmentDtoMapper;
import by.horunzhyn.godel.dto.jobtitle.JobTitleDtoMapper;
import by.horunzhyn.godel.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDtoMapper extends BaseDtoMapper<Employee, EmployeeDto> {

    @Autowired
    private DepartmentDtoMapper departmentDtoMapper;
    @Autowired
    private JobTitleDtoMapper jobTitleDtoMapper;

    public Employee mapPersistDtoToEntity(PersistEmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee entity = createEntity();
        fillPersistEntity(entity, dto);
        return entity;
    }

    private void fillPersistEntity(Employee entity, PersistEmployeeDto dto) {
        fillEmployeeEntityBaseData(entity, dto);
    }

    @Override
    protected EmployeeDto createDto() {
        return new EmployeeDto();
    }

    @Override
    protected void fillDto(EmployeeDto dto, Employee entity) {
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDepartment(departmentDtoMapper.mapEntityToDto(entity.getDepartment()));
        dto.setJobTitle(jobTitleDtoMapper.mapEntityToDto(entity.getJobTitle()));
        dto.setGender(entity.getGender());
        dto.setDateOfBirth(entity.getDateOfBirth());
    }

    @Override
    protected Employee createEntity() {
        return new Employee();
    }

    @Override
    protected void fillEntity(Employee entity, EmployeeDto dto) {
        fillEmployeeEntityBaseData(entity, dto);
        entity.setDepartment(departmentDtoMapper.mapDtoToEntity(dto.getDepartment()));
        entity.setJobTitle(jobTitleDtoMapper.mapDtoToEntity(dto.getJobTitle()));
    }

    private void fillEmployeeEntityBaseData(Employee entity, BaseEmployeeDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        entity.setDateOfBirth(dto.getDateOfBirth());
    }
}
