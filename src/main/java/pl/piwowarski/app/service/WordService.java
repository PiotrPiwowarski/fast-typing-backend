package pl.piwowarski.app.service;

import org.springframework.stereotype.Service;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultData;

import java.util.List;

@Service
public interface WordService {

    List<String> getRandomWordsList(int numberOfWords);

    ResultData verification(DataToVerificationDto dataToVerificationDto);
}
