package pl.piwowarski.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwowarski.app.dto.DataToVerificationDto;
import pl.piwowarski.app.dto.ResultDto;
import pl.piwowarski.app.dto.TextDto;
import pl.piwowarski.app.service.WordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/text")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TextController {

    private final WordService wordService;

    @GetMapping
    public ResponseEntity<TextDto> getRandomText(@RequestParam(name = "numberOfWords", defaultValue = "10") int numberOfWords) {
        String text = wordService.getRandomText(numberOfWords);
        TextDto textDto = TextDto.builder().patternText(text).build();
		return new ResponseEntity<>(textDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResultDto> getStatistics(@RequestBody DataToVerificationDto dataToVerificationDto) {
        ResultDto resultDto = wordService.getStatistics(dataToVerificationDto);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
