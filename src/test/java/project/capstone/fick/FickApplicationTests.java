package project.capstone.fick;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.fick.security.domain.Role;
import project.capstone.fick.service.xlsx.XlsxService;

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

//		xlsxService.xssfDownloadByUser(null, 1L);
		System.out.println(Role.USER.name());
		System.out.println(Role.ADMIN);
	}

}
