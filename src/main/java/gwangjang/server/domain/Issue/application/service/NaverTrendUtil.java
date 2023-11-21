package gwangjang.server.domain.Issue.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gwangjang.server.domain.Issue.application.dto.req.KeywordGroups;
import gwangjang.server.domain.Issue.application.dto.req.TrendReq;
import gwangjang.server.domain.Issue.application.dto.res.TrendRes;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class NaverTrendUtil {

    @Value("${naver.client-id}")
    private String clientId ; // 애플리케이션 클라이언트 아이디

    @Value("${naver.secret}")
    private String clientSecret;

    String apiUrl = "https://openapi.naver.com/v1/datalab/search";

    public TrendRes main(String issue)  {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        LocalDate today = LocalDate.now();
        LocalDate sixMonthsAgo = today.minusMonths(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedSixMonthsAgo = sixMonthsAgo.format(formatter);
        String formattedToday = today.format(formatter);
//
        TrendReq build = TrendReq.builder()
                .startDate(formattedSixMonthsAgo)
                .endDate(formattedToday)
                .timeUnit("week")
                .keywordGroups(Collections.singletonList(new KeywordGroups(issue)))
                .build();

        String requestBody = build.toString();
        // JSON 문자열을 TrendRes 객체로 변환
        String jsonResponse = post(apiUrl, requestHeaders, requestBody);

        ObjectMapper objectMapper = new ObjectMapper();
        TrendRes trendRes = new TrendRes();
        try {
            trendRes = objectMapper.readValue(jsonResponse, TrendRes.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trendRes;

    }
    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());

            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
