package gwangjang.server.domain.Issue.domain.service;

import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.domain.repository.IssueCustomRepository;
import gwangjang.server.domain.Issue.exception.NotFoundIssueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueCustomRepository issueQueryRepository;


    public IssueRes findIssueAndTopicById(Long issueId) {
        return issueQueryRepository.findIssueAndTopicById(issueId).orElseThrow(NotFoundIssueException::new);
    }
}
