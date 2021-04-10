package project.capstone.fick.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserDataJpaTest {

	@Autowired
	private UserRepository userRepository;

	private final Integer UID = 201511818;
	private final String userName = "박형우";
	private User savedUser;

	@BeforeEach
	void setUp() {
		savedUser = userRepository.save(User.builder()
			.UID(UID)
			.name(userName)
			.build());
	}

	@AfterEach
	public void tearDown() {
		userRepository.deleteAll();
	}

	@Test
	public void 유저_생성() {
		User foundUser = userRepository.findAll().get(0);

		// 저장된 값이 일치
		assertThat(savedUser.getName()).isEqualTo(userName);
		assertThat(savedUser.getUID()).isEqualTo(UID);

		// 저장된 유저와 같
		assertThat(savedUser).isEqualTo(foundUser);
		assertThat(savedUser.getUID()).isEqualTo(foundUser.getUID());
		assertThat(savedUser.getName()).isEqualTo(foundUser.getName());
	}

	@Test
	public void 유저_수정() {
		User user = userRepository.findById(savedUser.getId()).orElseThrow(IllegalArgumentException::new);

		final String expectedName = "우형박";
		user.updateUserName(expectedName);

		User foundUser = userRepository.findAll().get(0);

		assertThat(foundUser.getName()).isEqualTo(expectedName);
	}
}
