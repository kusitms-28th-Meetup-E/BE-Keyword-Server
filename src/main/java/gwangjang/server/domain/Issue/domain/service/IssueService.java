package gwangjang.server.domain.Issue.domain.service;

import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.application.dto.res.KeywordRes;
import gwangjang.server.domain.Issue.domain.entity.Keyword;
import gwangjang.server.domain.Issue.domain.repository.IssueCustomRepository;
import gwangjang.server.domain.Issue.domain.repository.IssueRepository;
import gwangjang.server.domain.Issue.domain.repository.KeywordRepository;
import gwangjang.server.domain.Issue.domain.repository.TopicRepository;
import gwangjang.server.domain.Issue.exception.NotFoundIssueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueCustomRepository issueQueryRepository;
    private final KeywordRepository keywordRepository;
    private final TopicRepository topicRepository;

    private final IssueRepository issueRepository;


    public IssueRes findIssueAndTopicById(Long issueId) {
        return issueQueryRepository.findIssueAndTopicById(issueId).orElseThrow(NotFoundIssueException::new);
    }
//    public KeywordRes findIssueAndTopicAndKeyword(Long issueId) {
//        Optional<IssueRes> issueResOptional = issueQueryRepository.findIssueAndTopicById(issueId);
//        List<KeywordRes> keywordResList = issueQueryRepository.findKeywordsByIssueId(issueId)
//                .map(Collections::singletonList)
//                .orElse(Collections.emptyList());
//
//        return issueResOptional.map(issueRes -> {
//            KeywordRes keywordRes = keywordResList.stream()
//                    .findFirst()
//                    .orElse(new KeywordRes());
//
//            return new KeywordRes(
//                    issueRes.getIssueId(),
//                    issueRes.getIssueTitle(),
//                    issueRes.getImgUrl(),
//                    issueRes.getTopicId(),
//                    issueRes.getTopicTitle(),
//                    keywordRes.getKeywordId(),
//                    keywordRes.getKeyword()
//            );
//        }).orElse(new KeywordRes());
//    }


    public List<KeywordRes> getKeywordsByIssueId(Long issueId) {
        IssueRes issue = issueQueryRepository.findIssueAndTopicById(issueId).orElseThrow(NotFoundIssueException::new);
        List<Keyword> keywords = keywordRepository.findByIssueId(issueId);

        return keywords.stream()
                .map(keyword -> KeywordRes.fromIssueAndKeyword(issue, keyword))
                .collect(Collectors.toList());
    }
}