package br.uff.sti.desafioinscricao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DesafioInscricaoApplicationTests {

	private Logger logger = LoggerFactory.getLogger(DesafioInscricaoApplicationTests.class);

	@Test
	public void contextLoads() {
		logger.info("Hello World!");
	}
	
}
