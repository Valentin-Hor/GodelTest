package by.horunzhyn.godel.dto.jobtitle;

import by.horunzhyn.godel.dto.BaseDtoMapper;
import by.horunzhyn.godel.entity.JobTitle;
import org.springframework.stereotype.Service;

@Service
public class JobTitleDtoMapper extends BaseDtoMapper<JobTitle, JobTitleDto> {

    @Override
    protected JobTitleDto createDto() {
        return new JobTitleDto();
    }

    @Override
    protected void fillDto(JobTitleDto dto, JobTitle entity) {
        dto.setTitle(entity.getTitle());
    }

    @Override
    protected JobTitle createEntity() {
        return new JobTitle();
    }

    @Override
    protected void fillEntity(JobTitle entity, JobTitleDto dto) {
        entity.setTitle(dto.getTitle());
    }
}
