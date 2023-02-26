package distove.chat.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value = "community", url = "http://distove.onstove.com")
@FeignClient(value = "community", url = "distove-community-service.sgs-dev.svc.cluster.local:8080")
public interface CommunityClient {

    @GetMapping("/community/web/category/list")
    List<CategoryInfoResponse> getCategoryIds(@RequestParam("channelIds") String channelIdsString);

    @GetMapping("/community/web/category")
    CategoryInfoResponse getCategoryId(@RequestParam("channelId") Long channelId);

    @GetMapping("/community/web/channel/{channelId}/member")
    boolean checkIsMember(@PathVariable Long channelId, @RequestParam Long userId);
}
