package gwangjang.server.domain.Issue.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class NaverTrendByIssueUseCase {

    private final NaverTrendUtil naverTrendUtil;

    public TrendRes getNaverTrend() {
        try {
            return naverTrendUtil.main();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
