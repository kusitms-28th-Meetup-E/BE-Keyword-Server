package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainBubbleData {
    private String name;
    private Long value;
}
