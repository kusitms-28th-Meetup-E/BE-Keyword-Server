package gwangjang.server.domain.Issue.application.dto.res;

import lombok.*;

import java.util.List;
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRes {

    String searchKeyword;
    String searchCount;

    List<SearchListRes> issueResList;
}
