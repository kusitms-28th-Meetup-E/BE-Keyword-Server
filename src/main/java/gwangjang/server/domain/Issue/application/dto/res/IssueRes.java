package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueRes {
    private String issueTitle;
    private String topicTitle;
    private String imgUrl;

    private Long issueId;
    private Long topicId;
}
