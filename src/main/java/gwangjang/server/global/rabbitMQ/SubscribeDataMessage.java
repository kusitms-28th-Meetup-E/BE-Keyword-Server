package gwangjang.server.global.rabbitMQ;

import java.io.Serializable;
import java.util.List;

public class SubscribeDataMessage implements Serializable {
    private List<SubscribeData> subscribeDataList;

    public SubscribeDataMessage() {
        // Default constructor needed for deserialization
    }

    public SubscribeDataMessage(List<SubscribeData> subscribeDataList) {
        this.subscribeDataList = subscribeDataList;
    }

    public List<SubscribeData> getSubscribeDataList() {
        return subscribeDataList;
    }

    public void setSubscribeDataList(List<SubscribeData> subscribeDataList) {
        this.subscribeDataList = subscribeDataList;
    }
}
