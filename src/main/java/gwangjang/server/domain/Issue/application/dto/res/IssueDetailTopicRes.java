package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetailTopicRes {
    private Long issueId;
    private String issueTitle;
    private String issueDetail;
    private String imgUrl;
    private Long topicId;
    private String topicTitle;
}
