package thelibrary.api.biblioteca.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import thelibrary.api.biblioteca.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
