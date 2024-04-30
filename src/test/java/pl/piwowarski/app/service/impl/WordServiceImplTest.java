package pl.piwowarski.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultDto;
import pl.piwowarski.app.entity.Word;
import pl.piwowarski.app.repository.WordRepository;
import pl.piwowarski.app.service.WordService;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
public class WordServiceImplTest {

    private final String text = "word word word word word";
    private final Word word = Word.builder()
            .id(1L)
            .word("word")
            .build();
    private final WordRepository wordRepository = Mockito.mock(WordRepository.class);
    private final Random random = Mockito.mock(Random.class);
    private final WordService wordService = new WordServiceImpl(wordRepository, random);
    private final DataToVerificationDto dataToVerificationDto = DataToVerificationDto.builder()
            .userText(text)
            .patternText(text)
            .time(10_000)
            .build();
    private final ResultDto resultDto = ResultDto.builder()
            .time(10.0)
            .wordsPerMinute(30)
            .keystrokesPerMinute(144)
            .accuracy(100.0)
            .build();

    @Test
    public void getRandomText_whenNumberOfWordsEqualsFiveThenReturnsTextOfLengthFive() {
        Mockito.when(wordRepository.countNumberOfWords()).thenReturn(1);
        Mockito.when(wordRepository.findById(1L)).thenReturn(Optional.of(word));
        Mockito.when(random.nextLong(1, 2)).thenReturn(1L);

        String result = wordService.getRandomText(5);

        Assertions.assertEquals(text, result);
    }

    @Test
    public void getStatistics_whenDataToVerificationThenResultDto() {
        ResultDto result = wordService.getStatistics(dataToVerificationDto);

        Assertions.assertEquals(resultDto, result);
    }
}
