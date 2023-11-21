package gwangjang.server.domain.Issue.application.dto.res;

import gwangjang.server.domain.Issue.domain.entity.Topic;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchListRes {
    private Long issueId;
    private String issueTitle;
    private String issueDetail;
    private String imgUrl;
    private String topicTitle;
    private String topicId;
}
