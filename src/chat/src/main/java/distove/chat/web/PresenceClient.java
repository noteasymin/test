package distove.chat.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "presence", url = "distove-presence-service.sgs-dev.svc.cluster.local:8080")
//@FeignClient(value = "presence", url = "http://distove.onstove.com")
//@FeignClient(value = "presence", url = "http://localhost:8080")

public interface PresenceClient {

    @PostMapping("/presence/web/update")
    void updateUserPresence(@RequestHeader("userId") Long userId,
                            @RequestParam("serviceInfo") String serviceInfo);
}
