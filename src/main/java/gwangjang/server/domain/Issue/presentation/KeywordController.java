package gwangjang.server.domain.Issue.presentation;


import gwangjang.server.domain.Issue.application.dto.res.MainBubbleRes;
import gwangjang.server.domain.Issue.application.dto.res.NaverTrendDto;
import gwangjang.server.domain.Issue.application.dto.res.TrendIssueGraphRes;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes;
import gwangjang.server.domain.Issue.application.service.KeywordSubscribeUseCase;
import gwangjang.server.domain.Issue.application.service.NaverTrendByIssueUseCase;
import gwangjang.server.global.response.SuccessResponse;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static gwangjang.server.domain.Issue.presentation.constant.IssueResponseMessage.GET_MAIN_BUBBLE_CHART;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class KeywordController {

    private final KeywordSubscribeUseCase keywordSubscribeUseCase;
    private final NaverTrendByIssueUseCase naverTrendByIssueUseCase;

    @GetMapping("/main")
    public ResponseEntity<SuccessResponse<List<MainBubbleRes>>> getIssueAndKeywordBySubscribe() {
        return ResponseEntity.ok(SuccessResponse.create(GET_MAIN_BUBBLE_CHART.getMessage(), this.keywordSubscribeUseCase.getBubbleData()));
    }

    @GetMapping("/trend/{issue}")
//    public ResponseEntity<SuccessResponse<List<TrendIssueGraphRes>>> getTrendDataByIssue() {
    public ResponseEntity<SuccessResponse<NaverTrendDto>> getTrendDataByIssue(@PathVariable("issue") String issue) {
        return ResponseEntity.ok(SuccessResponse.create(GET_MAIN_BUBBLE_CHART.getMessage(), this.naverTrendByIssueUseCase.getNaverTrend(issue)));
    }

}
