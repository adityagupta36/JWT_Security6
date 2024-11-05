package aditya.SpringSecurity.JWT_Security6.repo;

import aditya.SpringSecurity.JWT_Security6.Model.Student;
import aditya.SpringSecurity.JWT_Security6.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserName(String username);


}
