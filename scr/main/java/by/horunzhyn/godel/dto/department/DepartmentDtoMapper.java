package by.horunzhyn.godel.dto.department;

import by.horunzhyn.godel.dto.BaseDtoMapper;
import by.horunzhyn.godel.entity.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentDtoMapper extends BaseDtoMapper<Department, DepartmentDto> {

    @Override
    protected DepartmentDto createDto() {
        return new DepartmentDto();
    }

    @Override
    protected void fillDto(DepartmentDto dto, Department entity) {
        dto.setTitle(entity.getTitle());
    }

    @Override
    protected Department createEntity() {
        return new Department();
    }

    @Override
    protected void fillEntity(Department entity, DepartmentDto dto) {
        entity.setTitle(dto.getTitle());
    }
}
