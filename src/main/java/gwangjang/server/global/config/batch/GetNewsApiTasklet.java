package gwangjang.server.global.config.batch;

import gwangjang.server.domain.morpheme.domain.service.NewsAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetNewsApiTasklet implements Tasklet {
    private final NewsAPIService newsAPIService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String userApiResult = newsAPIService.naverAPI("test");

        // Store the result in JobExecutionContext
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
                .put("userApiResult", userApiResult);

        return RepeatStatus.FINISHED;
    }
}
