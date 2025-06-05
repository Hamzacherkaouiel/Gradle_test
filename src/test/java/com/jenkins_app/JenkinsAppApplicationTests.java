package com.jenkins_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class JenkinsAppApplicationTests {

	/*@DynamicPropertySource
	static void overrideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () ->
				"jdbc:postgresql://pg:5432/test_db"
		);
		registry.add("spring.datasource.username", ()-> "test_user");
		registry.add("spring.datasource.password", ()-> "test_pass");
	}*/


	@Test
	void contextLoads() {

		// Tu peux ajouter des assertions ici si nécessaire
	}
}