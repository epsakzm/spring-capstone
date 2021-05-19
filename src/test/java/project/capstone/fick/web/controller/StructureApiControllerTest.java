package project.capstone.fick.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StructureApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void 모든_structure_조회() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/structure/list"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
