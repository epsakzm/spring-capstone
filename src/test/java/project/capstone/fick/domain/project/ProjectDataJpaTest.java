package project.capstone.fick.domain.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.domain.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class ProjectDataJpaTest {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void 프로젝트_생성() {
		// given
		final Integer UID = 201511818;
		final String userName = "박형우";
		User savedUser = userRepository.save(User.builder()
			.UID(UID)
			.name(userName)
			.build());
		final String projectName = "프로젝트1";
		Project savedProject = projectRepository.save(Project.builder()
			.name(projectName)
			.build());

		savedUser.addProject(savedProject);
		savedProject.setUser(savedUser);

		List<User> userList = userRepository.findAll();
		List<Project> projectList = projectRepository.findAll();


	}
}
