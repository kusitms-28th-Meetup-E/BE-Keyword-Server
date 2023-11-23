package gwangjang.server.domain.Issue.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public List<TrendRes.Trend> getNaverTrend(String issue) {

        if(issue.length() > 13){
            issue = issue.substring(0, 7);
            System.out.println(issue);
        }

        String replace = issue.replace(" ", "");
        List<TrendRes.Trend> trendList = new ArrayList<>();

        TrendRes trendRes = naverTrendUtil.main(replace);


        String [] month = new String[] {"5월 1주차", "5월 2주차", "5월 3주차", "5월 4주차", "5월 5주차", "6월 1주차 ",
                "6월 2주차", "6월 3주차", "6월 4주차", "7월 1주차", "7월 2주차", "7월 3주차", "7월 4주차",
                "8월 1주차", "8월 2주차", "8월 3주차", "8월 4주차", "8월 5주차", "9월 1주차", "9월 2주차", "9월 3주차",
                "9월 4주차","10월 1주차","10월 2주차","10월 3주차","10월 4주차","10월 5주차","11월 1주차","11월 2주차","11월 3주차" } ;

        int i = 0;
        List<DataPoint> data = trendRes.getResults().get(0).getData();
        for (DataPoint datum : data) {
            datum.updatePeriod();
//             if (datum.getPeriod().equals(month[i])) {
                trendList.add(new TrendRes.Trend(datum.getPeriod(), datum.getRatio()));
//                i++;
//            } else{
//                trendList.add(new TrendRes.Trend(month[i],0));
//                i++;
//            }
        }


        return trendList;


    }

}
