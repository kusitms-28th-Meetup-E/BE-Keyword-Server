package gwangjang.server.domain.Issue.application.dto.req;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordGroups {
    private String groupName;
    private List<String> keywords = new ArrayList<>();

    public KeywordGroups(String groupName) {
        this.groupName = groupName;
        this.keywords.add(groupName);
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
