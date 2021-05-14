package project.capstone.fick.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.fick.service.xlsx.DownloadService;

@SpringBootTest
class ServiceUtilTest {

	@Autowired
	private ServiceUtil util;

	@Autowired
	private DownloadService service;
	@BeforeEach
	public void setUp() {

	}

	@Test
	public void initTest() {
		service.xssfDownload(null, null, 1L);
	}
}
