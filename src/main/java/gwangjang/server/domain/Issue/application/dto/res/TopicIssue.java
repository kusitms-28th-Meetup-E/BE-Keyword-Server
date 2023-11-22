package gwangjang.server.domain.Issue.application.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
public class TopicIssue{

    private String issueTitle;
    private String topicTitle;


    public TopicIssue(String issueTitle, String topicTitle) {
        this.issueTitle = issueTitle;
        this.topicTitle = topicTitle;
    }

}
