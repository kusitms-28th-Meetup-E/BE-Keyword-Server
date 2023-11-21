package gwangjang.server.domain.Issue.application.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrendRes {
    private String startDate;
    private String endDate;
    private String timeUnit;
    private List<Result> results;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Result {
        private String title;
        private List<String> keywords;
        private List<DataPoint> data;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DataPoint {
        private String period;
        private int ratio;

        public void updatePeriod() {
            // 날짜 파싱
            LocalDate date = LocalDate.parse(this.period);

            // 해당 월의 첫 번째 일요일 찾기
            LocalDate firstSunday = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));

            // 첫 번째 일요일이 해당 월의 첫째 날보다 이전인 경우, 다음 달의 첫째 일요일로 설정
            if (firstSunday.isBefore(date.withDayOfMonth(1))) {
                firstSunday = firstSunday.plusWeeks(1);
            }

            // 주차 계산
            int weekOfMonth = (date.getDayOfMonth() - firstSunday.getDayOfMonth()) / 7 + 1;

            // 결과 출력
            this.period = date.getMonthValue() + "월 " + weekOfMonth + "주차";
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Trend  {
        private String name;
        private int y;

    }
}
