package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetailRes {
    private String issueTitle;
    private String imgUrl;
    private String issueDetail;
    private Long issueId;
}
