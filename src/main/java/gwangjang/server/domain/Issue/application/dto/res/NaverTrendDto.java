package gwangjang.server.domain.Issue.application.dto.res;

import gwangjang.server.domain.Issue.application.dto.res.TrendRes;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
public class NaverTrendDto {
    private List<TrendRes.Trend> data;
    private String date;

    public NaverTrendDto(List<TrendRes.Trend> data, String date) {
        this.data = data;
        this.date = date;
    }
}
