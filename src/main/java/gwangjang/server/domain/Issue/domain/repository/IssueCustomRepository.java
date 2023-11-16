package gwangjang.server.domain.Issue.domain.repository;

import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IssueCustomRepository {
    Optional<IssueRes> findIssueAndTopicById(Long issueId);
}
