package project.capstone.fick.domain.crack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrackRepository extends JpaRepository<Crack, Long> {
}
