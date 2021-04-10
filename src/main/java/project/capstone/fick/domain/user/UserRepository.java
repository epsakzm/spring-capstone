package project.capstone.fick.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.capstone.fick.domain.project.Project;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.UID = :UID and u.name = :name")
	Optional<User> findByUIDAndName(@Param("UID") Integer id, @Param("name") String name);

	@Query("select u from User u join fetch u.projectList where u.id = :id")
	Optional<User> findUserByJoinFetch(@Param("id") Long id);
}
