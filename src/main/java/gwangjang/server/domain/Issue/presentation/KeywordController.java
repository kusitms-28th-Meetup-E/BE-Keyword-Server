package gwangjang.server.domain.Issue.presentation;


import gwangjang.server.domain.Issue.application.dto.res.MainBubbleRes;
import gwangjang.server.domain.Issue.application.service.KeywordSubscribeUseCase;
import gwangjang.server.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static gwangjang.server.domain.Issue.presentation.constant.IssueResponseMessage.GET_MAIN_BUBBLE_CHART;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class KeywordController {

    private final KeywordSubscribeUseCase keywordSubscribeUseCase;

    @GetMapping("/main")
    public ResponseEntity<SuccessResponse<List<MainBubbleRes>>> getIssueAndKeywordBySubscribe() {
        return ResponseEntity.ok(SuccessResponse.create(GET_MAIN_BUBBLE_CHART.getMessage(), this.keywordSubscribeUseCase.getBubbleData()));
    }

}
