package student.examples.ggengine.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import student.examples.ggengine.domain.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByUserName(@Param("userName") String userName);

	@Query("SELECT u FROM User u WHERE u.userName = :userName AND u.password = :hashedPassword")
	Optional<User> findByUserNameAndHashedPassword(@Param("userName") String userName,
			@Param("hashedPassword") String hashedPassword);
}
