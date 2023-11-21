package gwangjang.server.domain.Issue.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IssueResponseMessage {

    GET_ISSUE_SUCCESS("이슈 조회 완료"),
    GET_MAIN_BUBBLE_CHART("메인 페이지에 나타날 값 조회 완료했습니다.");

    private final String message;

}
