package gwangjang.server;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class KeywordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeywordServiceApplication.class, args);
	}

}
