package project.capstone.fick;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.crack.CrackRiskLevel;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.domain.user.UserRepository;
import project.capstone.fick.security.domain.Role;

import java.util.ArrayList;
import java.util.List;

@Profile("test")
@RequiredArgsConstructor
@Component
public class TestSaveSQLRunner implements ApplicationRunner {

	private final CrackRepository crackRepository;
	private final StructureRepository structureRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final PasswordEncoder passwordEncoder;

	// Test Entities
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User savedUser = userRepository.save(User.builder()
			.UID(201511818)
			.name("name")
			.role(Role.ADMIN)
			.password(passwordEncoder.encode("password"))
			.build());

		Project savedProject1 = projectRepository.save(Project.builder()
			.user(savedUser)
			.name("프로젝트1")
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.comment("설명설명 프로젝트설명1")
			.build());
		Project savedProject2 = projectRepository.save(Project.builder()
			.user(savedUser)
			.name("프로젝트2")
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("위치123123")
				.build())
			.comment("설명설명 프로젝트설명2")
			.build());
		Project savedProject3 = projectRepository.save(Project.builder()
			.user(savedUser)
			.name("프로젝트3")
			.location(Location.builder()
				.locationX(3.33)
				.locationY(4.44)
				.locationDetail("위치32131111")
				.build())
			.comment("설명설명 프로젝트설명3")
			.build());
		Project savedProject4 = projectRepository.save(Project.builder()
			.user(savedUser)
			.name("프로젝트4")
			.location(Location.builder()
				.locationX(5.55)
				.locationY(6.66)
				.locationDetail("위치위침ㅁㄴㅇㄹ")
				.build())
			.comment("설명설명 프로젝트설명4")
			.build());

		Structure savedStructure1 = structureRepository.save(Structure.builder()
			.name("structure1")
			.project(savedProject1)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("위치위치1919")
				.build())
			.comment("설명설명 구조물설명99")
			.height(292D)
			.build());
		Structure savedStructure2 = structureRepository.save(Structure.builder()
			.name("structure2")
			.project(savedProject2)
			.location(Location.builder()
				.locationX(2.221)
				.locationY(10.1011)
				.locationDetail("위치위치1010")
				.build())
			.comment("설명설명 구조물설명2222")
			.height(29.2D)
			.build());
		Structure savedStructure3 = structureRepository.save(Structure.builder()
			.name("structure3")
			.project(savedProject3)
			.location(Location.builder()
				.locationX(91.11)
				.locationY(82.22)
				.locationDetail("111.33")
				.build())
			.comment("설명설명 구조물설명55")
			.height(2.92D)
			.build());
		Structure savedStructure4 = structureRepository.save(Structure.builder()
			.name("structure4")
			.project(savedProject4)
			.location(Location.builder()
				.locationX(71.11)
				.locationY(92.22)
				.locationDetail("31.33")
				.build())
			.comment("설명설명구구구구")
			.height(2921.2D)
			.build());
		Structure savedStructure5 = structureRepository.save(Structure.builder()
			.name("structure5")
			.project(savedProject1)
			.location(Location.builder()
				.locationX(1.1331)
				.locationY(2.212)
				.locationDetail("223.33")
				.build())
			.comment("설명설명 구조물설명서르명서르명")
			.height(29.92D)
			.build());
		Structure savedStructure6 = structureRepository.save(Structure.builder()
			.name("structure6")
			.project(savedProject2)
			.location(Location.builder()
				.locationX(1.1122)
				.locationY(2.232)
				.locationDetail("3.3153")
				.build())
			.comment("설명설12명 구41조34물설명")
			.height(292.1111D)
			.build());

		Crack savedCrack1 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url1")
			.width(1.222D)
			.location(Location.builder()
				.locationX(1.411)
				.locationY(2.2412)
				.locationDetail("344.33")
				.build())
			.structure(savedStructure1)
			.riskLevel(CrackRiskLevel.LOW)
			.comment("설명설명 균열설명1123")
			.height(111.2D)
			.build());
		Crack savedCrack2 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url2")
			.width(1.3312D)
			.location(Location.builder()
				.locationX(15.11)
				.locationY(2.2422)
				.locationDetail("333.33")
				.build())
			.structure(savedStructure1)
			.riskLevel(CrackRiskLevel.MEDIUM)
			.comment("설명설명 균열설명ㅁㅁㄹㄹ")
			.height(111.222D)
			.build());
		Crack savedCrack3 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url3")
			.width(125.11D)
			.location(Location.builder()
				.locationX(51.11)
				.locationY(552.22)
				.locationDetail("3555.33")
				.build())
			.structure(savedStructure2)
			.riskLevel(CrackRiskLevel.HIGH)
			.comment("설명설명 균열설명")
			.height(1.299D)
			.build());
		Crack savedCrack4 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url4")
			.width(1.1111D)
			.location(Location.builder()
				.locationX(1.1155)
				.locationY(232.22)
				.locationDetail("34434ㅁㄴㅈ3.33")
				.build())
			.structure(savedStructure2)
			.riskLevel(CrackRiskLevel.LOW)
			.comment("설명설명 균열설명")
			.height(100.2D)
			.build());
		Crack savedCrack5 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url5")
			.width(1.1166D)
			.location(Location.builder()
				.locationX(1.1111)
				.locationY(2.222)
				.locationDetail("3ㅁㄹㅇㄴㄹ.33")
				.build())
			.structure(savedStructure2)
			.riskLevel(CrackRiskLevel.MEDIUM)
			.comment("설명설명 균열설명ㅁㅁ123123")
			.height(111.21231D)
			.build());
		Crack savedCrack6 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url6")
			.width(1.1133D)
			.location(Location.builder()
				.locationX(1.1133)
				.locationY(2.2233)
				.locationDetail("3.3344123")
				.build())
			.structure(savedStructure2)
			.riskLevel(CrackRiskLevel.HIGH)
			.comment("설명설명 균열설22222명")
			.height(999.29D)
			.build());

		savedUser.addProject(savedProject1);
		savedUser.addProject(savedProject2);
		savedUser.addProject(savedProject3);
		savedUser.addProject(savedProject4);

		savedProject1.addStructure(savedStructure1);
		savedProject2.addStructure(savedStructure2);
		savedProject3.addStructure(savedStructure3);
		savedProject4.addStructure(savedStructure4);
		savedProject1.addStructure(savedStructure5);
		savedProject2.addStructure(savedStructure6);

		savedStructure1.addCrack(savedCrack1);
		savedStructure1.addCrack(savedCrack2);
		savedStructure2.addCrack(savedCrack3);
		savedStructure2.addCrack(savedCrack4);
		savedStructure2.addCrack(savedCrack5);
		savedStructure2.addCrack(savedCrack6);
	}
}
