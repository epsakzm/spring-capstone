package project.capstone.fick.domain.structure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class StructureRepositoryTest {

	@Autowired
	private MockMvc mvc;

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
}
