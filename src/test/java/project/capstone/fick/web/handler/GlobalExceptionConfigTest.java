package project.capstone.fick.web.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.capstone.fick.service.CrackService;
import project.capstone.fick.web.CrackApiController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionConfigTest {


	@Autowired
	private MockMvc mvc;

	@Test
	public void exceptionHandlerTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/crack/22"))
			.andDo(print());
	}
}
