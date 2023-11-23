package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueBySubscribersRes {

    private Long categoryId;
    private String category; //영역
    private Long titleId;
    private String title; //주제
    private Long subscribeCount;
    private String imgUrl;

    public IssueBySubscribersRes(Long titleId, Long subscribeCount) {
        this.titleId = titleId;
        this.subscribeCount = subscribeCount;
    }

}
