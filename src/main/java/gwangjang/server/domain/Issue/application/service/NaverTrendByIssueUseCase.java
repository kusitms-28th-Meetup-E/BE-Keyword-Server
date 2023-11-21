package gwangjang.server.domain.Issue.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes.DataPoint;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NaverTrendByIssueUseCase {

    private final NaverTrendUtil naverTrendUtil;

    public List<TrendRes.Trend> getNaverTrend(String issue) {

        if(issue.length() > 13){
            issue = issue.substring(0, 7);
            System.out.println(issue);
        }

        String replace = issue.replace(" ", "");
        List<TrendRes.Trend> trendList = new ArrayList<>();

        TrendRes trendRes = naverTrendUtil.main(replace);

        trendRes.getResults().get(0).getData().stream().forEach(

                dataPoint ->{
                    dataPoint.updatePeriod();
                    trendList.add(new TrendRes.Trend(dataPoint.getPeriod(), dataPoint.getRatio()));
                }
            );


        return trendList;


    }

}
