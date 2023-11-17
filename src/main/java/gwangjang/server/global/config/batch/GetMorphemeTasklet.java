package gwangjang.server.global.config.batch;

import gwangjang.server.domain.morpheme.domain.service.MorphemeService;
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
public class GetMorphemeTasklet implements Tasklet {
    private final MorphemeService morphemeService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Retrieve the result from JobExecutionContext
        List<Token> analysisResult = (List<Token>) chunkContext.getStepContext().getStepExecution().getJobExecution()
                .getExecutionContext().get("analysisResult");

        // Use the result as needed
        morphemeService.saveOrUpdateWord(analysisResult, 3); // Assuming 3 is the issueId, replace with the correct value

        return RepeatStatus.FINISHED;
    }
}
