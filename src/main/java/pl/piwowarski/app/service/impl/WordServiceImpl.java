package pl.piwowarski.app.service.impl;

import org.springframework.stereotype.Service;
import pl.piwowarski.app.service.WordService;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {


    @Override
    public List<String> getRandomWordsList() {
        return List.of();
    }

    @Override
    public boolean verifySentence(String sentenceToVerification) {
        return sentenceToVerification.length() > 2;
    }
}
