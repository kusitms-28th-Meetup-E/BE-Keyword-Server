package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrendIssueGraphRes {
    private String week;
    private String value;
}
