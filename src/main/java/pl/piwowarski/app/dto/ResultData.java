package pl.piwowarski.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultData {

    private int correctWords;
    private int incorrectWords;
    private int correctCharacters;
    private int incorrectCharacters;
    private int accuracy;
}
