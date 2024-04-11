package pl.piwowarski.app.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WordService {

    List<String> getRandomWordsList();

    boolean verifySentence(String sentenceToVerification);
}
