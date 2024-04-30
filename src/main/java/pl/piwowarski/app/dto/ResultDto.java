package pl.piwowarski.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {

    private double time;
    private double wordsPerMinute;
    private double keystrokesPerMinute;
    private double accuracy;
}
