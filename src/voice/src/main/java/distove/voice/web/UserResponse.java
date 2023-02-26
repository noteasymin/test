package distove.voice.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class UserResponse implements Serializable {
    private Long id;
    private String nickname;
    private String profileImgUrl;
}
