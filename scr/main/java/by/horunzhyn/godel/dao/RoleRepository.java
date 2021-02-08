package by.horunzhyn.godel.dao;

import by.horunzhyn.godel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String name);

}
