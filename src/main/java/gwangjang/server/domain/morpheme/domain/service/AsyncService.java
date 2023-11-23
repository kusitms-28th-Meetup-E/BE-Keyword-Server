package gwangjang.server.domain.morpheme.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class AsyncService {
    private final Logger logger = LoggerFactory.getLogger(AsyncService.class);
    private final NewsAPIService newsAPIService;

    private final MorphemeService morphemeService;
    @Async
    public CompletableFuture<String> asyncMethodNews(String newsList1, int id) throws JsonProcessingException {
        logger.debug("ASYNC Start 1");
        List<Token> newsAnalysis1 =newsAPIService.analysis(newsList1);
        logger.debug("ASYNC Start");
        morphemeService.saveOrUpdateWord(newsAnalysis1, 100 );
        return null;
    }
}
