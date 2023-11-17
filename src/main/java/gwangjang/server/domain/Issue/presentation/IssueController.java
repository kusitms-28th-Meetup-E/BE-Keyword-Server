package gwangjang.server.domain.Issue.presentation;

import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.application.dto.res.KeywordRes;
import gwangjang.server.domain.Issue.domain.service.IssueService;
import gwangjang.server.domain.Issue.exception.NotFoundIssueException;
import gwangjang.server.domain.Issue.presentation.constant.IssueResponseMessage;
import gwangjang.server.global.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<SuccessResponse<IssueRes>> getIssueById(@PathVariable Long issueId) {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.findIssueAndTopicById(issueId) ));
    }
    @GetMapping("/issue/{issueId}/keyword")
    public ResponseEntity<SuccessResponse<List<KeywordRes>>> getKeywordById(@PathVariable Long issueId) {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.getKeywordsByIssueId(issueId)));
    }
}
