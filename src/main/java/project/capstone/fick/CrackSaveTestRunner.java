package project.capstone.fick;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;

import java.util.List;

@RequiredArgsConstructor
//@Component
public class CrackSaveTestRunner implements ApplicationRunner {

	private final CrackRepository crackRepository;
	private final StructureRepository structureRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Structure savedStructure = structureRepository.save(Structure.builder()
			.name("structure1")
			.build());
		Crack savedCrack = crackRepository.save(Crack.builder()
			.width(Double.valueOf("1.14"))
			.photoURI("photoURI")
			.structure(savedStructure)
			.build());

		List<Crack> cracks = crackRepository.findAll();
	}
}
