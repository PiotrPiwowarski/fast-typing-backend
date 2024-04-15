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

    private int correctWords;
    private int incorrectWords;
    private double accuracy;
    private int time;
    private int wordsPerMinute;
}
