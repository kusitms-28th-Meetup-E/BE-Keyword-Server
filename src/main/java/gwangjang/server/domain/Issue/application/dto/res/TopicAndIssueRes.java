package gwangjang.server.domain.Issue.application.dto.res;

import gwangjang.server.domain.Issue.domain.entity.Issue;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicAndIssueRes {
    private Long topicId;
    private String topicTitle;

    private List<TopicIssueRes> issueList;

}
