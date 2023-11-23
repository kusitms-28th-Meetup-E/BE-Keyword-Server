package gwangjang.server.domain.Issue.adapter.in.web.dto.get;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribersByIssueDto {
    
    private List<SubscribeData> data;
}




