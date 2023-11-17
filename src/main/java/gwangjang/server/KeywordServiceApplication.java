package gwangjang.server;

import gwangjang.server.domain.morpheme.presentation.MorphemeController;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EnableAsync
public class KeywordServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(KeywordServiceApplication.class, args);
	}

}
