package pl.piwowarski.app.service;

import org.springframework.stereotype.Service;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultDto;

@Service
public interface WordService {

    String getRandomText(int numberOfWords);

    ResultDto getStatistics(DataToVerificationDto dataToVerificationDto);
}
