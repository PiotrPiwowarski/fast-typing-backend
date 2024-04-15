package pl.piwowarski.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultDto;
import pl.piwowarski.app.exceptions.NoWordsWithSuchId;
import pl.piwowarski.app.repository.WordRepository;
import pl.piwowarski.app.service.WordService;

import java.util.Random;
import java.util.StringJoiner;

import static pl.piwowarski.app.exceptions.ErrorMessages.NO_WORDS_WITH_SUCH_ID;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final Random random;

    @Override
    public String getRandomText(int numberOfWords) {
        StringJoiner text = new StringJoiner(" ");
        int numberOfWordsInDatabase = wordRepository.countNumberOfWords();
        for (int i = 0; i < numberOfWords; i++) {
            String randomWord = wordRepository
                    .findById(random.nextLong(1, numberOfWordsInDatabase))
                    .orElseThrow(() -> new NoWordsWithSuchId(NO_WORDS_WITH_SUCH_ID))
                    .getWord();
            text.add(randomWord);
        }
        return text.toString();
    }

    @Override
    public ResultDto verification(DataToVerificationDto dataToVerificationDto) {
        String[] textToVerification = dataToVerificationDto.getUserText().split(" ");
        String[] pattern = dataToVerificationDto.getPatternText().split(" ");
        int time = dataToVerificationDto.getTime();
        return prepareResultData(textToVerification, pattern, time);
    }
    
    private ResultDto prepareResultData(String[] textToVerification, String[] pattern, int time) {
        if(textToVerification.length > pattern.length) {
            return countResultData(pattern.length, textToVerification, pattern, time);
        } else {
            return countResultData(textToVerification.length, textToVerification, pattern, time);
        }
    }

    private ResultDto countResultData(int size, String[] textToVerification, String[] pattern, int time) {
        int incorrectWordsCounter = Math.abs(pattern.length - textToVerification.length);
        int correctWordsCounter = 0;
        for (int i = 0; i < size; i++) {
            incorrectWordsCounter += textToVerification[i].equals(pattern[i]) ? 0 : 1;
            correctWordsCounter += textToVerification[i].equals(pattern[i]) ? 1 : 0;
        }
        time = (int) Math.round((time/1000.0));
        int wordsPerMinute = 0;
        if(textToVerification.length < pattern.length) {
			wordsPerMinute = (60 * textToVerification.length) / time;
        } else {
            wordsPerMinute = (60 * pattern.length) / time;
        }
        return ResultDto.builder()
                .incorrectWords(incorrectWordsCounter)
                .correctWords(correctWordsCounter)
                .accuracy(Math.round(((double) correctWordsCounter / (correctWordsCounter + incorrectWordsCounter)) * 100.0))
                .time(time)
                .wordsPerMinute(wordsPerMinute)
                .build();
    }
}
