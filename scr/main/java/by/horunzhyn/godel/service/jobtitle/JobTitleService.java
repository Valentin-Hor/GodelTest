package by.horunzhyn.godel.service.jobtitle;

import by.horunzhyn.godel.dao.JobTitleRepository;
import by.horunzhyn.godel.entity.JobTitle;
import by.horunzhyn.godel.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobTitleService extends BaseServiceImpl<JobTitle, JobTitleRepository> {

    @Autowired
    private JobTitleRepository jobTitleRepository;

    public JobTitle update(Long id, String newTitle) {
        Optional<JobTitle> optional = findOne(id);

        if (!optional.isPresent()) {
            return null;
        }

        JobTitle foundJobTitle = optional.get();
        foundJobTitle.setTitle(newTitle);
        return save(foundJobTitle);
    }

    @Override
    protected JobTitleRepository getRepository() {
        return jobTitleRepository;
    }

}
