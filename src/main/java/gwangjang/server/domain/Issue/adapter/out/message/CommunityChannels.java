//package gwangjang.server.domain.Issue.adapter.out.message;
//import gwangjang.server.domain.Issue.adapter.in.web.dto.get.SubscribeData;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.MessageChannel;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//@Configuration
//@RequiredArgsConstructor
//public class CommunityChannels {
//
//    //    @Bean
////    public Function<String, String> uppercase() { // 메시지를 받아 처리한 후 결과를 반환
////    return message -> {
////        // 메시지 처리 로직
////        return message.toUpperCase();
////    };
////}
//    @Bean
//    public Consumer<List<SubscribeData>> receiveFromMember() { //메시지를 받아 처리하는 데 사용되지만 반환 값이 없
//        return message -> {
//            // 메시지 처리 로직 ( 역직렬화 )
//            System.out.println("Received: " + message);
//        };
//    }
//}
