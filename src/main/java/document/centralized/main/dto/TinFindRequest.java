package document.centralized.main.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TinFindRequest {

    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String surname;
    @NotBlank
    @Pattern(regexp = "^[0-3][0-9][.][01][0-9][.][12][0-9][0-9][0-9]$")
    private final String birthday;
    @NotBlank
    private final String docType;
    @NotBlank
    @Pattern(regexp = "^[0-9][0-9] [0-9][0-9] [0-9][0-9][0-9][0-9][0-9][0-9]$")
    private final String docNum;
    @NotBlank
    @Pattern(regexp = "^[0-3][0-9][.][01][0-9][.][12][0-9][0-9][0-9]$")
    private final String docDateTake;

}