package gwangjang.server.global.config.batch;

import gwangjang.server.domain.morpheme.domain.service.MorphemeService;
import gwangjang.server.domain.morpheme.domain.service.NewsAPIService;
import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class BatchJobConfig {

    private final NewsAPIService newsApiService;
    private final MorphemeService morphemeService;


    @Bean(name="apiJob")
    public Job apiJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("apiJob", jobRepository)
                .start(getNewsApiTasklet(jobRepository, platformTransactionManager))
                .next(getAnalysisTasklet(jobRepository,platformTransactionManager))
                .next(getMorphemeApiStep(jobRepository,platformTransactionManager))
                .build();
    }
    @Bean
    public Step getNewsApiTasklet(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("newsApiService", jobRepository)
                .tasklet(new GetNewsApiTasklet(newsApiService), platformTransactionManager)
                .build();
    }
    @Bean
    public Step getAnalysisTasklet(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("newsApiService", jobRepository)
                .tasklet(new GetAnalysisTasklet(newsApiService), platformTransactionManager)
                .build();
    }
    @Bean
    public Step getMorphemeApiStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("morphemeService", jobRepository)
                .tasklet(new GetMorphemeTasklet(morphemeService), platformTransactionManager)
                .build();
    }
    @Bean
    public Job stepNextConditionalJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("stepNextConditionalJob" ,jobRepository)
                .start(getNewsApiTasklet(jobRepository, transactionManager))
                .on("*") // Any exit status
                .to(getAnalysisTasklet(jobRepository, transactionManager))
                .from(getAnalysisTasklet(jobRepository, transactionManager))
                .on("*") // Any exit status
                .to(getMorphemeApiStep(jobRepository, transactionManager))
                .from(getMorphemeApiStep(jobRepository, transactionManager))
                .on("*") // Any exit status
                .to(getNewsApiTasklet(jobRepository, transactionManager))
                .end()
                .build();
    }


}