package distove.chat.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInfoResponse implements Serializable {
    private Long id;
    private List<Long> channelIds;
}
