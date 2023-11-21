package gwangjang.server.domain.Issue.domain.repository;

import gwangjang.server.domain.Issue.application.dto.res.IssueDetailTopicRes;
import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.application.dto.res.KeywordRes;
import gwangjang.server.domain.Issue.domain.entity.Issue;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IssueCustomRepository {
    Optional<IssueRes> findIssueAndTopicById(Long issueId);
    Optional<KeywordRes> findKeywordsByIssueId(Long issueId);
    List<IssueDetailTopicRes> getAllIssueDetailTopicRes();
    List<Issue> search(String keyword);
}
