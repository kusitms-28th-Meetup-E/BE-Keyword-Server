package gwangjang.server.domain.Issue.application.mapper;

import gwangjang.server.domain.Issue.application.dto.res.SearchListRes;
import gwangjang.server.domain.Issue.domain.entity.Issue;
import gwangjang.server.global.annotation.Mapper;

@Mapper
public class IssueMapper {
    public SearchListRes mapToSearchListRes(Issue issue) {
        SearchListRes searchListRes = new SearchListRes();
        searchListRes.setIssueId(issue.getId());
        searchListRes.setIssueTitle(issue.getIssueTitle());
        searchListRes.setIssueDetail(issue.getIssueDetail());
        searchListRes.setImgUrl(issue.getImgUrl());

        // Topic이 null이 아닌 경우에만 매핑
        if (issue.getTopic() != null) {
            searchListRes.setTopicTitle(issue.getTopic().getTopicTitle());
            searchListRes.setTopicId(String.valueOf(issue.getTopic().getId()));
        }

        return searchListRes;
    }
}
