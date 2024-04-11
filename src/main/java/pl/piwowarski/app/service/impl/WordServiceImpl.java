package pl.piwowarski.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultData;
import pl.piwowarski.app.exceptions.NoWordsWithSuchId;
import pl.piwowarski.app.repository.WordRepository;
import pl.piwowarski.app.service.WordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.piwowarski.app.exceptions.ErrorMessages.NO_WORDS_WITH_SUCH_ID;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final Random random;

    @Override
    public List<String> getRandomWordsList(int numberOfWords) {
        List<String> wordsList = new ArrayList<>(numberOfWords);
        int numberOfWordsInDatabase = wordRepository.countNumberOfWords();
        for (int i = 0; i < numberOfWords; i++) {
            String randomWord = wordRepository
                    .findById(random.nextLong(numberOfWordsInDatabase))
                    .orElseThrow(() -> new NoWordsWithSuchId(NO_WORDS_WITH_SUCH_ID))
                    .getWord();
            wordsList.add(randomWord);
        }
        return wordsList;
    }

    @Override
    public ResultData verification(DataToVerificationDto dataToVerificationDto) {
        String[] textToVerification = dataToVerificationDto.getTextToVerification().split(" ");
        String[] pattern = dataToVerificationDto.getPattern().split(" ");
        return wrongWordsCounter(textToVerification, pattern);
    }
    
    private ResultData wrongWordsCounter(String[] textToVerification, String[] pattern) {
        int incorrectWordsCounter = Math.abs(pattern.length - textToVerification.length);
        int correctWordsCounter = 0;
        int incorrectCharactersCounter = 0;
        int correctCharactersCounter = 0;
        if(textToVerification.length > pattern.length) {
            for (int i = 0; i < pattern.length; i++) {
				incorrectWordsCounter += textToVerification[i].equals(pattern[i]) ? 0 : 1;
                correctWordsCounter += textToVerification[i].equals(pattern[i]) ? 1 : 0;
                incorrectCharactersCounter += incorrectCharactersCounter(textToVerification[i].split(""), pattern[i].split(""));
                correctCharactersCounter += correctCharactersCounter(textToVerification[i].split(""), pattern[i].split(""));
            }
        } else {
            for (int i = 0; i < textToVerification.length; i++) {
                incorrectWordsCounter += textToVerification[i].equals(pattern[i]) ? 0 : 1;
                correctWordsCounter += textToVerification[i].equals(pattern[i]) ? 1 : 0;
                incorrectCharactersCounter += incorrectCharactersCounter(textToVerification[i].split(""), pattern[i].split(""));
                correctCharactersCounter += correctCharactersCounter(textToVerification[i].split(""), pattern[i].split(""));
            }
        }
        return ResultData.builder()
                .incorrectWords(incorrectWordsCounter)
                .correctWords(correctWordsCounter)
                .incorrectCharacters(incorrectCharactersCounter)
                .correctCharacters(correctCharactersCounter)
                .build();
    }

    private int incorrectCharactersCounter(String[] toVerification, String[] pattern) {
        int wrongLettersCounter = 0;
        if(toVerification.length > pattern.length) {
            for (int i = 0; i < pattern.length; i++) {
                wrongLettersCounter += toVerification[i].equals(pattern[i]) ? 0 : 1;
            }
        } else {
            for (int i = 0; i < toVerification.length; i++) {
                wrongLettersCounter += toVerification[i].equals(pattern[i]) ? 0 : 1;
            }
        }
        return wrongLettersCounter;
    }

    private int correctCharactersCounter(String[] toVerification, String[] pattern) {
        int correctCharactersCounter = 0;
        if(toVerification.length > pattern.length) {
            for (int i = 0; i < pattern.length; i++) {
                correctCharactersCounter += toVerification[i].equals(pattern[i]) ? 1 : 0;
            }
        } else {
            for (int i = 0; i < toVerification.length; i++) {
                correctCharactersCounter += toVerification[i].equals(pattern[i]) ? 1 : 0;
            }
        }
        return correctCharactersCounter;
    }
}
