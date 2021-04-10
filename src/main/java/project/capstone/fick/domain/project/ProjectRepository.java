package project.capstone.fick.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("select p from Project p join fetch p.structureList where p.id = :id")
	Optional<Project> findProjectByJoinFetch(@Param("id") Long id);
}
