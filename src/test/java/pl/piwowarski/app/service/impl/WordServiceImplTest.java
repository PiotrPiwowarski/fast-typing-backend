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

    private final WordRepository wordRepository = mock(WordRepository.class);
    private final WordService wordService = new WordServiceImpl(wordRepository, new Random());

    @Test
    public void getRandomWordsListTest() {

    }

    @Test
    public void verificationTest() {

    }
}
