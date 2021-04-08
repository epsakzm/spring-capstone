package project.capstone.fick;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TestSaveSQLRunner implements ApplicationRunner {

	private final CrackRepository crackRepository;

	private final StructureRepository structureRepository;

	private final UserRepository userRepository;

	private final ProjectRepository projectRepository;

	// Test Entities
	@Profile("test")
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User savedUser = userRepository.save(User.builder()
			.UID(201511818)
			.name("nnaammee")
			.build());

		Project savedProject1 = projectRepository.save(Project.builder()
			.user(savedUser)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Project savedProject2 = projectRepository.save(Project.builder()
			.user(savedUser)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Project savedProject3 = projectRepository.save(Project.builder()
			.user(savedUser)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Project savedProject4 = projectRepository.save(Project.builder()
			.user(savedUser)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());

		Structure savedStructure1 = structureRepository.save(Structure.builder()
			.name("structure1")
			.project(savedProject1)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Structure savedStructure2 = structureRepository.save(Structure.builder()
			.name("structure2")
			.project(savedProject2)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Structure savedStructure3 = structureRepository.save(Structure.builder()
			.name("structure3")
			.project(savedProject3)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Structure savedStructure4 = structureRepository.save(Structure.builder()
			.name("structure4")
			.project(savedProject4)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Structure savedStructure5 = structureRepository.save(Structure.builder()
			.name("structure5")
			.project(savedProject1)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());
		Structure savedStructure6 = structureRepository.save(Structure.builder()
			.name("structure6")
			.project(savedProject2)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.build());

		Crack savedCrack1 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url1")
			.width(1.11D)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.structure(savedStructure1)
			.build());
		Crack savedCrack2 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url2")
			.width(1.11D)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.structure(savedStructure1)
			.build());
		Crack savedCrack3 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url3")
			.width(1.11D)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.structure(savedStructure2)
			.build());
		Crack savedCrack4 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url4")
			.width(1.11D)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.structure(savedStructure2)
			.build());
		Crack savedCrack5 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url5")
			.width(1.11D)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.structure(savedStructure2)
			.build());
		Crack savedCrack6 = crackRepository.save(Crack.builder()
			.photoUrl("photo.url6")
			.width(1.11D)
			.location(Location.builder()
				.locationX(1.11)
				.locationY(2.22)
				.locationDetail("3.33")
				.build())
			.structure(savedStructure2)
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
