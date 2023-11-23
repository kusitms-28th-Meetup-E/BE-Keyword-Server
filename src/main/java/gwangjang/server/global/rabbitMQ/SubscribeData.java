package gwangjang.server.global.rabbitMQ;

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
