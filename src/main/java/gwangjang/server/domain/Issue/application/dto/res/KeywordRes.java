package gwangjang.server.domain.Issue.application.dto.res;

import gwangjang.server.domain.Issue.domain.entity.Issue;
import gwangjang.server.domain.Issue.domain.entity.Keyword;
import gwangjang.server.domain.Issue.domain.entity.Topic;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeywordRes {
    private Long issueId;
    private String issueTitle;
    private String imgUrl;
    private Long topicId;
    private String topicTitle;
    private Long keywordId;
    private String keyword;


    public static KeywordRes fromIssueAndKeyword(IssueRes issue, Keyword keyword) {
        return KeywordRes.builder()
                .issueId(issue.getIssueId())
                .issueTitle(issue.getIssueTitle())
                .imgUrl(issue.getImgUrl())
                .topicId(issue.getTopicId())
                .topicTitle(issue.getTopicTitle())
                .keywordId(keyword.getId())
                .keyword(keyword.getKeyword())
                .build();
    }


}
