package gwangjang.server;


import gwangjang.server.domain.Issue.adapter.in.web.dto.get.SubscribeData;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
//import org.springframework.cloud.stream.annotation.EnableBinding;


@EnableFeignClients
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EnableAsync
@EnableDiscoveryClient
public class KeywordServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(KeywordServiceApplication.class, args);
	}


}
