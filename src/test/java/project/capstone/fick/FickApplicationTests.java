package project.capstone.fick;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.fick.service.xlsx.XlsxService;
import project.capstone.fick.web.dto.crack.CrackExcelResponseDto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

// test code
// junit5
// Mockito
// resttemplate
//


@SpringBootTest
class FickApplicationTests {

	@Autowired
	private XlsxService xlsxService;
	@Test
	void contextLoads() {

		xlsxService.xssfDownload(null, 1L);
	}

}
