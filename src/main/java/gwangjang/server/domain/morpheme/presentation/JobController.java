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
}
