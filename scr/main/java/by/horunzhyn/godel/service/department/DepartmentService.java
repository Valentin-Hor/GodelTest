package by.horunzhyn.godel.service.department;

import by.horunzhyn.godel.dao.DepartmentRepository;
import by.horunzhyn.godel.entity.Department;
import by.horunzhyn.godel.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService extends BaseServiceImpl<Department,DepartmentRepository> {

    @Autowired
    private DepartmentRepository DepartmentRepository;

    @Override
    protected DepartmentRepository getRepository(){
        return DepartmentRepository;
    }

    public Department update (Long id, String newTitle) {
        Optional<Department> optional = findOne(id);

        if (!optional.isPresent()) {
            return null;
        }

        Department foundDepartment = optional.get();
        foundDepartment.setTitle(newTitle);
        return save(foundDepartment);
    }


}
