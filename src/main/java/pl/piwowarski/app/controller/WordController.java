package pl.piwowarski.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultData;
import pl.piwowarski.app.service.WordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wordsList")
public class WordController {

    private final WordService wordService;

    @GetMapping
    public ResponseEntity<List<String>> getRandomWordsList(@RequestParam(name = "words", defaultValue = "10") String words) {
        int numberOfWords = Integer.parseInt(words);
        List<String> wordsList = wordService.getRandomWordsList(numberOfWords);
		return new ResponseEntity<>(wordsList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResultData> verification(@RequestBody DataToVerificationDto dataToVerificationDto) {
        ResultData resultData = wordService.verification(dataToVerificationDto);
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }
}
