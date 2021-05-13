package project.capstone.fick.domain.structure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StructureRepositoryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private StructureRepository repository;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void readStructureTest() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/api/v1/structure/3"))
//			.andDo(print());

		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder.fromUriString("http://101.101.216.124")
			.path("/api/v1/structure/2")
			.build()
			.toUri();
		ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);

		System.out.println("====================getBody================");
		System.out.println(forEntity.getBody());
	}

	@Test
	public void 특정ID_structure_조회() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
			.path("/api/v1/structure/1")
			.build()
			.toUri();
		ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);

		System.out.println("port : " + port);
		System.out.println(response);
	}

	@Test
	public void selectAllStructure() {
		repository.findAll().stream().map(Structure::getId).forEach(System.out::println);
		Optional<Structure> byId = repository.findById(1L);
		Optional<Structure> structureByJoinFetch = repository.findStructureByJoinFetch(1L);

		System.out.println("==========byId=============");
		System.out.println(byId.get().getId());
		System.out.println("==========byfetch=============");
		System.out.println(structureByJoinFetch.get().getId());
	}

	@Autowired
	private ProjectRepository projectRepository;

	@Test
	public void select() {
		Optional<Project> byId = projectRepository.findById(1L);
		Optional<Project> projectByJoinFetch = projectRepository.findProjectByJoinFetch(1L);

		System.out.println(byId);
		System.out.println(projectByJoinFetch);
	}
}
