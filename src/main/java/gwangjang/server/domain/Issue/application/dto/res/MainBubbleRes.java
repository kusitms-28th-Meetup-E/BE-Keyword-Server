package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainBubbleRes {
    private String name;
    private List<MainBubbleData> data;
}
