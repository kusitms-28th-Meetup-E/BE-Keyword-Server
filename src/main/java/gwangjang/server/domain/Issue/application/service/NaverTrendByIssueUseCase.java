package gwangjang.server.domain.Issue.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import gwangjang.server.domain.Issue.application.dto.res.NaverTrendDto;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes.DataPoint;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class NaverTrendByIssueUseCase {

    private final NaverTrendUtil naverTrendUtil;

    public NaverTrendDto getNaverTrend(String issue) {

        if(issue.length() > 13){
            issue = issue.substring(0, 7);
            System.out.println(issue);
        }

        String replace = issue.replace(" ", "");

        TrendRes trendRes = naverTrendUtil.main(replace);

        String [] month = new String[] {"5월 1주차", "5월 2주차", "5월 3주차", "5월 4주차", "5월 5주차", "6월 1주차",
                "6월 2주차", "6월 3주차", "6월 4주차", "7월 1주차", "7월 2주차", "7월 3주차", "7월 4주차","7월 5주차",
                "8월 1주차", "8월 2주차", "8월 3주차", "8월 4주차", "8월 5주차", "9월 1주차", "9월 2주차", "9월 3주차",
                "9월 4주차","10월 1주차","10월 2주차","10월 3주차","10월 4주차","10월 5주차","11월 1주차","11월 2주차","11월 3주차" } ;

        List<TrendRes.Trend> trendList = new ArrayList<>();


        List<DataPoint> data = trendRes.getResults().get(0).getData();

        data.stream().forEach(
                dataPoint -> dataPoint.updatePeriod()
        );

        int max = 0;
        String maxDate = null;
        int j = 0;
        for (int i = 0; i < month.length; i++) {
            if (j < data.size() && data.get(j).getPeriod().equals(month[i])) {
                if (max < data.get(j).getRatio()) {
                    max = data.get(j).getRatio();
                    maxDate = data.get(j).getPeriod();
                }
                trendList.add(new TrendRes.Trend(data.get(j).getPeriod(), data.get(j).getRatio()));
                j++;
            } else {
                trendList.add(new TrendRes.Trend(month[i], 0));
            }
        }

        return new NaverTrendDto(trendList,maxDate);


    }

}
