package gwangjang.server.domain.Issue.domain.repository;

import gwangjang.server.domain.Issue.domain.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    List<Keyword> findByIssueId(Long issueId);


}
