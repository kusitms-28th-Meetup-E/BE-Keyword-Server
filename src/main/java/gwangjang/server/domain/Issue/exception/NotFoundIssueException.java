package gwangjang.server.domain.Issue.exception;

import gwangjang.server.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundIssueException extends IssueException{
    public NotFoundIssueException() {
        super(ErrorCode.NOT_FOUND_ISSUE_ERROR, HttpStatus.NOT_FOUND);
    }
}
