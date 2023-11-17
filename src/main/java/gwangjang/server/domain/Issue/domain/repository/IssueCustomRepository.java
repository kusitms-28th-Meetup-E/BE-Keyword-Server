package gwangjang.server.domain.Issue.domain.repository;

import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.application.dto.res.KeywordRes;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IssueCustomRepository {
    Optional<IssueRes> findIssueAndTopicById(Long issueId);
    public Optional<KeywordRes> findKeywordsByIssueId(Long issueId);
}
