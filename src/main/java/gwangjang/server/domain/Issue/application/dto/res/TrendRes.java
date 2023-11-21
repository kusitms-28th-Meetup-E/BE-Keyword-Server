package gwangjang.server.domain.Issue.application.dto.res;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

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
        private double ratio;
    }
}
