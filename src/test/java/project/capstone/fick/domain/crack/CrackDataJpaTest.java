package project.capstone.fick.domain.crack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CrackDataJpaTest {

	@Autowired
	private CrackRepository crackRepository;

	@Autowired
	private StructureRepository structureRepository;

	@Test
	public void 크랙_생성_테스트() {
		//given
		Structure save = structureRepository.save(new Structure("Structure1"));

		crackRepository.save(
			Crack.builder()
				.photoURI("URI")
				.width(Double.valueOf("1.11"))
				.structure(save)
			.build());

		//when
		List<Crack> all = crackRepository.findAll();

		//then
		System.out.println("==========================================");
		System.out.println(all.get(0));
		System.out.println("createdDate = " + all.get(0).getCreatedDate());
		System.out.println("modifiedDate = " + all.get(0).getModifiedDate());
		System.out.println("==========================================");
	}
}
