package project.capstone.fick.domain.structure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StructureRepository extends JpaRepository<Structure, Long> {

	@Query("select s from Structure s left join fetch s.crackList where s.id = :id")
	Optional<Structure> findStructureByJoinFetch(@Param("id") Long id);
}
