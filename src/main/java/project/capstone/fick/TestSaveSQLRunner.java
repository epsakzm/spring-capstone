package project.capstone.fick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;

@Component
public class TestSaveSQLRunner implements ApplicationRunner {

	@Autowired
	private CrackRepository crackRepository;

	@Autowired
	private StructureRepository structureRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Structure structure = structureRepository.save(Structure.builder()
			.name("structure")
			.build());
	}
}
