package gwangjang.server.global.config.batch;

import gwangjang.server.domain.morpheme.domain.service.NewsAPIService;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetAnalysisTasklet implements Tasklet {
    private final NewsAPIService newsAPIService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Retrieve the result from JobExecutionContext
        String userApiResult = (String) chunkContext.getStepContext().getStepExecution().getJobExecution()
                .getExecutionContext().get("userApiResult");

        // Use the result as needed
        List<Token> analysisResult = newsAPIService.analysis(userApiResult);

        // Store the analysis result in JobExecutionContext
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
                .put("analysisResult", analysisResult);

        return RepeatStatus.FINISHED;
    }
}
