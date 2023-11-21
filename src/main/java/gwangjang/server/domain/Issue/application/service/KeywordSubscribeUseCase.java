package gwangjang.server.domain.Issue.application.service;

import gwangjang.server.domain.Issue.application.dto.res.MainBubbleData;
import gwangjang.server.domain.Issue.application.dto.res.MainBubbleRes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class KeywordSubscribeUseCase {

    public List<MainBubbleRes> getBubbleData() {
        List<MainBubbleRes> mainBubbleRes = new ArrayList<>();
        List<MainBubbleData> mainBubbleData1 = new ArrayList<>();
        mainBubbleData1.add(new MainBubbleData("해양", 300L));
        mainBubbleData1.add(new MainBubbleData("방류", 200L));
        mainBubbleData1.add(new MainBubbleData("원전", 200L));
        mainBubbleData1.add(new MainBubbleData("도쿄", 200L));
        mainBubbleData1.add(new MainBubbleData("원자력", 200L));
        mainBubbleRes.add(new MainBubbleRes("후쿠시마 오염수", mainBubbleData1));

        List<MainBubbleData> mainBubbleData2 = new ArrayList<>();
        mainBubbleData2.add(new MainBubbleData("물류", 300L));
        mainBubbleData2.add(new MainBubbleData("택배", 200L));
        mainBubbleData2.add(new MainBubbleData("센터", 200L));
        mainBubbleData2.add(new MainBubbleData("과로사", 200L));
        mainBubbleData2.add(new MainBubbleData("사고", 200L));
        mainBubbleRes.add(new MainBubbleRes("쿠팡 노동자 사망", mainBubbleData2));

        List<MainBubbleData> mainBubbleData3 = new ArrayList<>();
        mainBubbleData3.add(new MainBubbleData("의료", 300L));
        mainBubbleData3.add(new MainBubbleData("증원", 200L));
        mainBubbleData3.add(new MainBubbleData("정부", 200L));
        mainBubbleData3.add(new MainBubbleData("의사", 200L));
        mainBubbleData3.add(new MainBubbleData("지역", 200L));
        mainBubbleRes.add(new MainBubbleRes("의대 정원 확대", mainBubbleData3));

        List<MainBubbleData> mainBubbleData4 = new ArrayList<>();
        mainBubbleData4.add(new MainBubbleData("최대", 300L));
        mainBubbleData4.add(new MainBubbleData("52", 200L));
        mainBubbleData4.add(new MainBubbleData("정부", 200L));
        mainBubbleData4.add(new MainBubbleData("발표", 200L));
        mainBubbleData4.add(new MainBubbleData("노동부", 200L));
        mainBubbleRes.add(new MainBubbleRes("주 69시간 근로제도 개편", mainBubbleData4));

        return mainBubbleRes;
    }
}
