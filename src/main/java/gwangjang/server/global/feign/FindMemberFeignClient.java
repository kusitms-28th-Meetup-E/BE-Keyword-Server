package gwangjang.server.global.feign;

import gwangjang.server.domain.Issue.application.dto.res.IssueBySubscribersRes;
import gwangjang.server.global.response.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="MEMBERSERVICE")
public interface FindMemberFeignClient {
    @GetMapping("/subscribe/issue")
    ResponseEntity<SuccessResponse<List<IssueBySubscribersRes>>> getIssuesBySubscribers(@RequestHeader(value = "user-id") String socialId);
}