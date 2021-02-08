package by.horunzhyn.godel.dao;

import by.horunzhyn.godel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByLogin(String login);

}