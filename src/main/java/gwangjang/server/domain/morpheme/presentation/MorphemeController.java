package gwangjang.server.domain.morpheme.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import gwangjang.server.domain.morpheme.domain.service.AsyncService;
import gwangjang.server.domain.morpheme.domain.service.MorphemeService;
import gwangjang.server.domain.morpheme.domain.service.NewsAPIService;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/keyword")
@RequiredArgsConstructor

public class MorphemeController {
    private final Logger logger = LoggerFactory.getLogger(MorphemeController.class);
    private final NewsAPIService newsAPIService;
    private final MorphemeService morphemeService;
    private final AsyncService asyncSerivce;

    //@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public String analysis(List<String> keywords) throws JsonProcessingException, InterruptedException, ExecutionException {
        logger.debug("ASYNC Start");

        // Create a list of CompletableFuture for each keyword
        List<CompletableFuture<Void>> futures = keywords.parallelStream()
                .map(keyword -> processKeywordAsync(keyword))
                .collect(Collectors.toList());

        // Combine all CompletableFuture into one
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        // Wait for all asynchronous tasks to complete
        try {
            allOf.get();
            logger.debug("비동기 종료");
            return "success";
        } catch (InterruptedException | ExecutionException e) {
            logger.error("비동기 작업 중 오류 발생", e);
            return "error";
        }
    }

    private CompletableFuture<Void> processKeywordAsync(String keyword) {
        return CompletableFuture.runAsync(() -> {
            try {
                String newsList = newsAPIService.naverAPI(keyword);
                int randomValue = new Random().nextInt(1000);
                asyncSerivce.asyncMethodNews(newsList, randomValue);
            } catch (JsonProcessingException e) {
                logger.error("키워드 처리 중 오류 발생", e);
            }
        });
    }

//
//    //@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
//    public List<String> processKeywords(List<String> keywords) throws InterruptedException, ExecutionException {
//        // 키워드를 병렬로 처리하는 CompletableFuture 리스트 생성
//        List<CompletableFuture<String>> futures = keywords.parallelStream()
//                .map(this::processKeywordAsync)
//                .collect(Collectors.toList());
//
//        // CompletableFuture 리스트를 조합하여 모든 작업이 완료될 때까지 대기
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(
//                futures.toArray(new CompletableFuture[0])
//        );
//
//        // 모든 작업이 완료되면 결과를 수집
//        CompletableFuture<List<String>> allResults = allOf.thenApply(v ->
//                futures.stream()
//                        .map(CompletableFuture::join)
//                        .collect(Collectors.toList())
//        );
//
//        // 최종 결과 반환
//        return allResults.get();
//    }
//
//    // 각 키워드를 비동기적으로 처리하는 메서드
//    private CompletableFuture<String> processKeywordAsync(String keyword) {
//        return CompletableFuture.supplyAsync(() -> {
//            // 여기에 실제 키워드 처리 로직 추가
//            // 예시로 간단히 키워드를 가공하여 반환하는 로직을 작성
//            return "Processed: " + keyword;
//        });
//    }
//
//    //@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
//    public String analysis(String msg) throws JsonProcessingException {
//        logger.debug("ASYNC Start");
//        String newsList1 = newsAPIService.naverAPI(msg);
//        String newsList2 = newsAPIService.naverAPI(msg);
//
//
//        CompletableFuture<Void> future1 = asyncSerivce.asyncMethodNews(newsList1);
//        CompletableFuture<Void> future2 = asyncSerivce.asyncMethodNews2(newsList2);
//        //CompletableFuture<Void> future3 = asyncMethodNews3(newsList3);
//
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(
//                asyncSerivce.asyncMethodNews(newsList1),
//                asyncSerivce.asyncMethodNews2(newsList2)
//                //asyncMethodNews3(newsList3)
//        );
//        // 모든 비동기 작업이 완료되기를 기다리고 "success" 반환
//        try {
//            allOf.get(); // 대기
//            logger.debug("비동기 종료");
//            return "success";
//        } catch (InterruptedException | ExecutionException e) {
//            logger.error("비동기 작업 중 오류 발생", e);
//            return "error";
//        }
//    }




}
