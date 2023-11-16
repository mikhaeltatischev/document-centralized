package document.centralized.main.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class TinGetRequest {

    private final String requestId;
    private final String token1;
    private final String captchaRequired;

}