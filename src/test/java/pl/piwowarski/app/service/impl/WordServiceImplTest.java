package pl.piwowarski.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultDto;
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
            new Word(1L, "word1")
    ));

    @Test
    public void getRandomWordsListTest() {
        WordRepository wordRepository = mock(WordRepository.class);

        when(wordRepository.findById(1L)).thenReturn(Optional.of(words.get(0)));
        when(wordRepository.countNumberOfWords()).thenReturn(2);

        WordService wordService = new WordServiceImpl(wordRepository, new Random());

        assertEquals("word1 word1 word1 word1", wordService.getRandomText(4));
    }

    @Test
    public void verificationTest() {
        WordRepository wordRepository = mock(WordRepository.class);

        WordService wordService = new WordServiceImpl(wordRepository, new Random());

        assertEquals(
                wordService.verification(new DataToVerificationDto("Ala ma kota", "Ala ma kota", 10000)),
                new ResultDto(3, 0, 100.0, 10, 18));
    }
}
