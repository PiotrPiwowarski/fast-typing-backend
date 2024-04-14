package pl.piwowarski.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataToVerificationDto {

    private String userText;
    private String patternText;
    private int time;
}
