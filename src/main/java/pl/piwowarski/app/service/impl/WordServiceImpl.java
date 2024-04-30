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
                    .findById(random.nextLong(1, numberOfWordsInDatabase + 1))
                    .orElseThrow(() -> new NoWordsWithSuchId(NO_WORDS_WITH_SUCH_ID))
                    .getWord();
            text.add(randomWord);
        }
        return text.toString();
    }

    @Override
    public ResultDto getStatistics(DataToVerificationDto dataToVerificationDto) {
        int mistakes = Math.abs(dataToVerificationDto.getPatternText().length() - dataToVerificationDto.getUserText().length());
        int properLength;

        if(dataToVerificationDto.getPatternText().length() > dataToVerificationDto.getUserText().length()) {
            properLength = dataToVerificationDto.getPatternText().length();
            mistakes += countMistakes(properLength, dataToVerificationDto);
        } else {
            properLength = dataToVerificationDto.getUserText().length();
            mistakes += countMistakes(properLength, dataToVerificationDto);
        }
        return createResultStatistics(mistakes,
                dataToVerificationDto.getUserText().split("").length,
                dataToVerificationDto.getTime(),
                dataToVerificationDto.getUserText().split(" ").length);
    }

    private int countMistakes(int properLength, DataToVerificationDto dataToVerificationDto) {
        int countMistakes = 0;
        String[] userText = dataToVerificationDto.getUserText().split("");
        String[] patternText = dataToVerificationDto.getPatternText().split("");
        for(int i = 0; i < properLength; i++) {
            if(i < userText.length && i < patternText.length && !userText[i].equals(patternText[i])) {
                countMistakes++;
            }
        }
        return countMistakes;
    }

    private ResultDto createResultStatistics(int mistakes, int letters, int time, int words) {
        double timeInSeconds = (double) time /1000;
        return ResultDto.builder()
                .time(timeInSeconds)
                .wordsPerMinute(wordsPerMinute(words, timeInSeconds))
                .keystrokesPerMinute(lettersPerMinute(letters, timeInSeconds))
                .accuracy(accuracy(mistakes, letters))
                .build();
    }

    private double wordsPerMinute(int words, double time) {
        return words * 60 / time;
    }

    private double lettersPerMinute(int letters, double time) {
        return letters * 60 /time;
    }

    private double accuracy(int mistakes, int letters) {
        double result = 100 - ((double) mistakes * 100 / letters);
        return result < 0 ? 0 : result;
    }
}
