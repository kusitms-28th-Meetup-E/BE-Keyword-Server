package gwangjang.server.domain.Issue.application.dto.res;

import gwangjang.server.domain.Issue.domain.entity.Issue;
import gwangjang.server.domain.Issue.domain.entity.Keyword;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalRes {
    private Long issueId;
    private String issueTitle;
    private String imgUrl;
    private Long keywordId;
    private String keyword;

    public static List<TotalRes> fromIssuesAndKeywords(List<Issue> issues, List<Keyword> keywords) {
        return keywords.stream()
                .map(keyword -> {
                    Issue relatedIssue = findIssueById(issues, keyword.getIssue().getId());
                    return TotalRes.fromIssueAndKeyword(relatedIssue, keyword);
                })
                .collect(Collectors.toList());
    }

    private static Issue findIssueById(List<Issue> issues, Long issueId) {
        return issues.stream()
                .filter(issue -> issue.getId().equals(issueId))
                .findFirst()
                .orElse(null); // 원하는 예외 처리 로직으로 변경 가능
    }

    private static TotalRes fromIssueAndKeyword(Issue issue, Keyword keyword) {
        TotalRes totalRes = new TotalRes();
        totalRes.setIssueId(issue.getId());
        totalRes.setIssueTitle(issue.getIssueTitle());
        totalRes.setImgUrl(issue.getImgUrl());
        totalRes.setKeywordId(keyword.getId());
        totalRes.setKeyword(keyword.getKeyword());
        return totalRes;
    }

}
