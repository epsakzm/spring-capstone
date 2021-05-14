package project.capstone.fick.domain.crack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrackRepository extends JpaRepository<Crack, Long> {

	@Query("select count(c) from Crack c where c.structure.id = :id")
	Long countCrackByStructureId(@Param("id")Long structureId);

	@Query("select c from Crack c where c.structure.id = :id")
	List<Crack> findCrackByStructureId(@Param("id")Long structureId);
}
