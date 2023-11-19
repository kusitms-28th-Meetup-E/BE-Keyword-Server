package gwangjang.server.domain.Issue.domain.repository;

import gwangjang.server.domain.Issue.domain.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>, IssueCustomRepository{
    List<Issue> findByTopicId(Long topicId);
}
