package gwangjang.server.domain.Issue.presentation;

import gwangjang.server.domain.Issue.application.dto.res.*;
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
    @GetMapping("/issue/all")
    public ResponseEntity<SuccessResponse<List<TotalRes>>> getAll() {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.getTotals()));
    }
    @GetMapping("/topic/all")
    public ResponseEntity<SuccessResponse<List<TopicAndIssueRes>>> getTopicAndIssue() {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.getTopicAndIssueList()));
    }

    @GetMapping("/issueDetail/all")
    public ResponseEntity<SuccessResponse<List<IssueDetailRes>>> getIssueDetailAll() {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.getAllIssue()));
    }

    @GetMapping("/topic/issueDetail/all")
    public ResponseEntity<SuccessResponse<List<IssueDetailTopicRes>>> getIssueDetailAndTopicAll() {
        return ResponseEntity.ok(SuccessResponse.create(IssueResponseMessage.GET_ISSUE_SUCCESS.getMessage(),this.issueService.getAllIssueDetailTopicRes()));
    }

}
