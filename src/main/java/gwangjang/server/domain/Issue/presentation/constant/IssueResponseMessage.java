package gwangjang.server.domain.Issue.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IssueResponseMessage {

    GET_ISSUE_SUCCESS("이슈 조회 완료");

    private final String message;

}
