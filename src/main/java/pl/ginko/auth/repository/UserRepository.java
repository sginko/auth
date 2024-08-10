package pl.ginko.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ginko.auth.models.MyUsers;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUsers, Long> {

    Optional<MyUsers> findByName(String username);
}
