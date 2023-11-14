package gwangjang.server.domain.morpheme.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import gwangjang.server.domain.morpheme.domain.service.MorphemeService;
import gwangjang.server.domain.morpheme.domain.service.NewsAPIService;
import io.swagger.annotations.ApiOperation;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keyword")
@RequiredArgsConstructor
public class MorphemeController {

    private final NewsAPIService newsAPIService;
    private final MorphemeService morphemeService;
    //@GetMapping("/analysis/{msg}")

    @Scheduled(cron = "55 3 * * 2")
    //@GetMapping("/test")
    public String analysis() throws JsonProcessingException {
        String newsList1 = newsAPIService.naverAPI("주 69시간 근로시간 제도 개편");
        String newsList2 = newsAPIService.naverAPI("이태원 참사");
        String newsList3 = newsAPIService.naverAPI("국민연금 개혁");

        asyncMethodNews(newsList1);
        asyncMethodNews2(newsList2);
        asyncMethodNews3(newsList3);
      return "success";
    }
    @Async
    public void asyncMethodNews(String newsList1) throws JsonProcessingException {
        List<Token> newsAnalysis1 =newsAPIService.analysis(newsList1);
        morphemeService.saveOrUpdateWord(newsAnalysis1, 100 );
    }
    @Async
    public void asyncMethodNews2(String newsList2) throws JsonProcessingException {

        List<Token> newsAnalysis2 =newsAPIService.analysis(newsList2);
        morphemeService.saveOrUpdateWord(newsAnalysis2, 200);
    }
    @Async
    public void asyncMethodNews3(String newsList3) throws JsonProcessingException {
        List<Token> newsAnalysis3 =newsAPIService.analysis(newsList3);
        morphemeService.saveOrUpdateWord(newsAnalysis3, 300);
    }
}
