package pl.piwowarski.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultData;
import pl.piwowarski.app.entity.Word;
import pl.piwowarski.app.repository.WordRepository;
import pl.piwowarski.app.service.WordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
public class WordServiceImplTest {

    private final List<Word> words = new ArrayList<>(List.of(
            new Word(1L, "word1"),
            new Word(2L, "word2")
    ));

    @Test
    public void getRandomWordsListTest() {
        WordRepository wordRepository = mock(WordRepository.class);

        when(wordRepository.findById(1L)).thenReturn(Optional.of(words.get(0)));
        when(wordRepository.findById(2L)).thenReturn(Optional.of(words.get(1)));
        when(wordRepository.countNumberOfWords()).thenReturn(2);

        WordService wordService = new WordServiceImpl(wordRepository, new Random());

        assertEquals(10, wordService.getRandomWordsList(10).size());
    }

    @Test
    public void verificationTest() {
        WordRepository wordRepository = mock(WordRepository.class);

        WordService wordService = new WordServiceImpl(wordRepository, new Random());

        assertEquals(
                wordService.verification(new DataToVerificationDto("Ala ma kota", "Ala ma kota")),
                new ResultData(3.0, 0, 100.0));
    }
}
