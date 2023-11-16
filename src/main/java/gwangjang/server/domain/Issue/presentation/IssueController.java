package gwangjang.server.domain.Issue.presentation;

import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.domain.service.IssueService;
import gwangjang.server.domain.Issue.exception.NotFoundIssueException;
import gwangjang.server.domain.Issue.presentation.constant.IssueResponseMessage;
import gwangjang.server.global.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping("/keyword/issue/{issueId}")
    public ResponseEntity<SuccessResponse<IssueRes>> getIssueById(@PathVariable Long issueId) {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.findIssueAndTopicById(issueId) ));

    }
}
