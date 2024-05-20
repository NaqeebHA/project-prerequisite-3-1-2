package habsida.spring.boot_security.demo.repo;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);
}
