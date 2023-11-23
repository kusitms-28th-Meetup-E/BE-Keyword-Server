//package gwangjang.server.domain.Issue.adapter.in.message.model;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import gwangjang.server.domain.Issue.adapter.in.web.dto.get.SubscribersByIssueDto;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.common.header.Headers;
//import org.apache.kafka.common.serialization.Deserializer;
//
//import java.util.List;
//import java.util.Map;
//
//@RequiredArgsConstructor
//public class CommunityCustomDeserializer implements Deserializer<List<SubscribersByIssueDto>> {
//
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {
//        Deserializer.super.configure(configs, isKey);
//    }
//
//    @Override
//    public List<SubscribersByIssueDto> deserialize(String topic, byte[] data) {
//        if (data == null) {
//            return null;
//        }
//
//        try {
//            return objectMapper.readValue(data, new TypeReference<List<SubscribersByIssueDto>>() {});
//        } catch (Exception e) {
//            throw new RuntimeException("Deserialization error: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<SubscribersByIssueDto> deserialize(String topic, Headers headers, byte[] data) {
//        return Deserializer.super.deserialize(topic, headers, data);
//    }
//
//    @Override
//    public void close() {
//        Deserializer.super.close();
//    }
//}
