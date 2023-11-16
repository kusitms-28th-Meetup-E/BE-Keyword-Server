package gwangjang.server.domain.morpheme.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@RequiredArgsConstructor
public class AsyncService {
    private final Logger logger = LoggerFactory.getLogger(AsyncService.class);
    private final NewsAPIService newsAPIService;

    private final MorphemeService morphemeService;
    @Async
    public CompletableFuture<Void> asyncMethodNews(String newsList1) throws JsonProcessingException {
        logger.debug("ASYNC Start 1");
        List<Token> newsAnalysis1 =newsAPIService.analysis(newsList1);
        logger.debug("ASYNC Start");
        morphemeService.saveOrUpdateWord(newsAnalysis1, 100 );
        return null;
    }
    @Async
    public CompletableFuture<Void> asyncMethodNews2(String newsList2) throws JsonProcessingException {
        logger.debug("ASYNC Start 2");
        List<Token> newsAnalysis2 =newsAPIService.analysis(newsList2);
        logger.debug("ASYNC Start 3");
        morphemeService.saveOrUpdateWord(newsAnalysis2, 200);
        return null;
    }
    @Async
    public CompletableFuture<Void> asyncMethodNews3(String newsList3) throws JsonProcessingException {
        logger.debug("ASYNC Start 4 ");
        List<Token> newsAnalysis3 =newsAPIService.analysis(newsList3);
        logger.debug("ASYNC Start 5");
        morphemeService.saveOrUpdateWord(newsAnalysis3, 300);
        return null;
    }
}
