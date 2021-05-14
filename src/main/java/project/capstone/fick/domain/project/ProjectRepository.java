package project.capstone.fick.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("select p from Project p left join fetch p.structureList where p.id = :id")
	Optional<Project> findProjectByJoinFetch(@Param("id") Long id);

	@Query("select count(p) from Project p where p.user.id = :id")
	Long countProjectByUserId(@Param("id")Long id);

	@Query("select p from Project p where p.user.id = :id")
	List<Project> findProjectByUserId(@Param("id")Long userId);
}
