package gwangjang.server.domain.morpheme.presentation;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@Configuration
//@EnableBatchProcessing
public class JobController {

    private final JobLauncher jobLauncher;
    private final Job apiJob; // Inject the job bean you want to execute

    @Autowired
    public JobController(JobLauncher jobLauncher, Job apiJob) {
        this.jobLauncher = jobLauncher;
        this.apiJob = apiJob;
    }

    @GetMapping("/runJob")
    public String runJob() throws Exception {
        JobExecution jobExecution = jobLauncher.run(apiJob, new JobParameters());
        return "Job Execution Status: " + jobExecution.getStatus();
    }

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private AsyncService asyncService;
//
//    @Autowired
//    private MorphemeService morphemeService;
//
//    @Autowired
//    private NewsAPIService newsAPIService;
//
//    @Bean
//    public ItemReader<String> keywordReader() {
//        // Implement your ItemReader to provide keywords (e.g., from a database or file)
//        // This example assumes a simple List<String> as input
//        List<String> keywords = Arrays.asList("keyword1", "keyword2", "keyword3");
//        return new ListItemReader<>(keywords);
//    }
//
//    @Bean
//    public ItemProcessor<String, List<Token>> keywordProcessor() {
//        // Implement your ItemProcessor to process keywords and return a list of tokens
//        return keyword -> {
//            String newsList = newsAPIService.naverAPI(keyword);
//            int randomValue = new Random().nextInt(1000);
//            asyncService.asyncMethodNews(newsList, randomValue);
//            // You might want to return some result or null, depending on your needs
//            return null;
//        };
//    }
//
//    @Bean
//    public ItemWriter<List<Token>> keywordWriter() {
//        // Implement your ItemWriter to handle the processing result
//        // In this example, we don't do anything with the processed tokens
//        return tokens -> {
//            // You can perform additional actions here if needed
//        };
//    }
//
//    @Bean
//    public Step keywordStep() {
//        return stepBuilderFactory.get("keywordStep")
//                .<String, List<Token>>chunk(1)
//                .reader(keywordReader())
//                .processor(keywordProcessor())
//                .writer(keywordWriter())
//                .taskExecutor(new SimpleAsyncTaskExecutor()) // Enable parallel processing
//                .build();
//    }
//
//    @Bean
//    public Job keywordJob() {
//        return jobBuilderFactory.get("keywordJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(keywordStep())
//                .end()
//                .build();
//    }
}


