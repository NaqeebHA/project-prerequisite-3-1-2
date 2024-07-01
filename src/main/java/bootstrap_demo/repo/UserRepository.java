package bootstrap_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import bootstrap_demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email= :username")
    Optional<User> findUserAndRolesByEmail(@Param("username") String username);
}
