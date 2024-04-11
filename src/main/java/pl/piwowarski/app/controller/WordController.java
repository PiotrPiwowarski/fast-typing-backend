package pl.piwowarski.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwowarski.app.service.WordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sentence")
public class WordController {

    private final WordService wordService;

    @GetMapping
    public ResponseEntity<List<String>> getRandomSentence() {
        List<String> words = wordService.getRandomWordsList();
		return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> verifySentence(@RequestBody String sentenceToVerification) {
        boolean result = wordService.verifySentence(sentenceToVerification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
