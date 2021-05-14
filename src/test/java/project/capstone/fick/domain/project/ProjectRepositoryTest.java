package project.capstone.fick.domain.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository projectRepository;

	@Test
	public void count_query() {
		System.out.println(projectRepository.countProjectByUserId(1L));
	}
}
