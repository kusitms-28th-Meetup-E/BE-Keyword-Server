//package gwangjang.server.domain.Issue.adapter.in.message;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.function.Supplier;
//@Configuration
//@RequiredArgsConstructor
//public class MemberChannels {
//
//    @Bean
//    public Supplier<Message<String>> sendToMember() {  // 메시지를 주기적으로 생성하고 보내는 데 사용
//        return () -> {
//            // 메시지 생성 로직
//            String jsonMessage = "{\"key\":\"value\"}";
//
//            // dto to string
//            return MessageBuilder.withPayload(jsonMessage).build();
//        };
//    }
//
//}
