package distove.community.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "chat", url = "distove-chat-service.sgs-dev.svc.cluster.local:8080")
//@FeignClient(value = "chat", url = "http://distove.onstove.com")
//@FeignClient(value = "presence", url = "http://localhost:8082")

public interface ChatClient {

    @PostMapping("/chat/web/connection/server/{serverId}")
    void createConnection(@PathVariable Long serverId, @RequestParam Long channelId);

    @DeleteMapping("/chat/web/clear/{channelId}")
    void clearAll(@PathVariable Long channelId);

    @DeleteMapping("/chat/web/clear/list")
    void clearChatConnections(@RequestParam String channelIds);

}
