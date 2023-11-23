package gwangjang.server.domain.Issue.adapter.in.web.dto.get;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeData {
    private Long issueId;
    private Long count;
}
