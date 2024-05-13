package gwangjang.server.domain.Issue.application.service;

import gwangjang.server.domain.Issue.application.dto.res.IssueBySubscribersRes;
import gwangjang.server.domain.Issue.application.dto.res.MainBubbleData;
import gwangjang.server.domain.Issue.application.dto.res.MainBubbleRes;
import gwangjang.server.domain.Issue.domain.service.IssueService;
import gwangjang.server.global.feign.FindMemberFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class KeywordSubscribeUseCase {
    private final IssueService issueService;
    private final FindMemberFeignClient findMemberFeignClient;
    public List<MainBubbleRes> getBubbleData() {
        List<MainBubbleRes> mainBubbleRes = new ArrayList<>();
        List<MainBubbleData> mainBubbleData1 = new ArrayList<>();
        mainBubbleData1.add(new MainBubbleData("후쿠시마 오염수", 356L));
        mainBubbleData1.add(new MainBubbleData("일회용품 사용 규제 시행", 123L));
        mainBubbleRes.add(new MainBubbleRes("환경", mainBubbleData1));

        List<MainBubbleData> mainBubbleData2 = new ArrayList<>();
        mainBubbleData2.add(new MainBubbleData("SPC 불매 운동", 335L));
        mainBubbleData2.add(new MainBubbleData("쿠팡 노동자 사망", 121L));
        mainBubbleData2.add(new MainBubbleData("주 69시간 근로시간 제도 개편", 50L));
        mainBubbleRes.add(new MainBubbleRes("일자리/노동", mainBubbleData2));

        List<MainBubbleData> mainBubbleData3 = new ArrayList<>();
        mainBubbleData3.add(new MainBubbleData("이태원 참사", 90L));
        mainBubbleData3.add(new MainBubbleData("국민연금 개혁", 323L));
        mainBubbleRes.add(new MainBubbleRes("주거/사회안전망", mainBubbleData3));

        List<MainBubbleData> mainBubbleData4 = new ArrayList<>();
        mainBubbleData4.add(new MainBubbleData("의대 정원 확대", 156L));
        mainBubbleData4.add(new MainBubbleData("서이초 교사 사건", 400L));
        mainBubbleRes.add(new MainBubbleRes("교육", mainBubbleData4));

        return mainBubbleRes;
    }
//    public List<MainBubbleRes> getBubbleData() {
//        List<IssueBySubscribersRes> subscribersRes = findMemberFeignClient.getIssuesBySubscribers("guest").getBody().getData();
//        return mapToMainBubbleResList(subscribersRes);
//    }
//
//    public List<MainBubbleRes> mapToMainBubbleResList(List<IssueBySubscribersRes> subscribersRes) {
//        return subscribersRes.stream()
//                .collect(Collectors.groupingBy(IssueBySubscribersRes::getCategory))
//                .entrySet().stream()
//                .map(entry -> {
//                    MainBubbleRes mainBubbleRes = new MainBubbleRes();
//                    mainBubbleRes.setName(entry.getKey());
//                    List<MainBubbleData> mainBubbleDataList = entry.getValue().stream()
//                            .map(this::mapToMainBubbleData)
//                            .collect(Collectors.toList());
//
//                    mainBubbleRes.setData(mainBubbleDataList);
//                    return mainBubbleRes;
//                })
//                .collect(Collectors.toList());
//    }
//
//    private MainBubbleData mapToMainBubbleData(IssueBySubscribersRes issueBySubscribersRes) {
//        MainBubbleData mainBubbleData = new MainBubbleData();
//        mainBubbleData.setName(issueBySubscribersRes.getTitle());
//
//        // Calculate subscriber count within the specified ratio (e.g., 50%)
//        mainBubbleData.setValue((long) calculateRatioBasedSubscriberCount(issueBySubscribersRes.getSubscribeCount()));
//        System.out.println(issueBySubscribersRes.getSubscribeCount());
//        return mainBubbleData;
//    }
//
//    private int calculateRatioBasedSubscriberCount(double ratio) {
//        int minSubscriberCount = 100;
//        int maxSubscriberCount = 400;
//
//        // Calculate subscriber count within the specified ratio
//        return (int) ((maxSubscriberCount * 1.5 ) * ratio)+100;
//
//    }
}
