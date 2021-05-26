package project.capstone.fick.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.crack.CrackRiskLevel;
import project.capstone.fick.web.dto.crack.CrackUpdateRequestDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CrackApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CrackRepository crackRepository;

	@Test
	@Transactional
	public void update_test() throws Exception {
		/*
		as-is
		isCrack = true
		comment = 설명설명 균열설명1123
		riskLevel = LOW
		 */
		Crack crack = crackRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
		System.out.println("==========================BEFORE========================");
		System.out.println(crack.getIsCrack());
		System.out.println(crack.getComment());
		System.out.println(crack.getRiskLevel());

		CrackUpdateRequestDto dto = new CrackUpdateRequestDto(null, "바뀐 코멘트", null);
		/*
		to-be
		isCrack = false
		comment = 바뀐 코멘트
		riskLevel = HIGH
		 */
		mvc.perform(MockMvcRequestBuilders.put("/api/v1/crack/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk());

		Crack crack2 = crackRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
		System.out.println("==========================AFTER========================");
		System.out.println(crack2.getIsCrack());
		System.out.println(crack2.getComment());
		System.out.println(crack2.getRiskLevel());
	}
}
