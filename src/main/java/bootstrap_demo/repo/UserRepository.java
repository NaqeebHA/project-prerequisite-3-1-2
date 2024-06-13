package bootstrap_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import bootstrap_demo.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
