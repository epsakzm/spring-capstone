package project.capstone.fick.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.structure.StructureRepository;
import project.capstone.fick.web.dto.crack.CrackSaveRequestDto;
import project.capstone.fick.web.dto.user.UserRequestDto;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CrackApiControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private CrackRepository crackRepository;

	@Autowired
	private StructureRepository structureRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {

	}

	@AfterEach
	void tearDown() {
		crackRepository.deleteAll();
		structureRepository.deleteAll();
	}

	private void print(Object object) {
		System.out.println("===================================");
		System.out.println("===================================");
		System.out.println("===================================");
		System.out.println(object);
		System.out.println("===================================");
		System.out.println("===================================");
		System.out.println("===================================");
	}

	public void 크랙_생성() throws Exception{
	}

	public void test() throws Exception {
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println(objectMapper.writeValueAsString(new UserRequestDto(null, null)));
		System.out.println("======================================");
		System.out.println("======================================");
	}

}
