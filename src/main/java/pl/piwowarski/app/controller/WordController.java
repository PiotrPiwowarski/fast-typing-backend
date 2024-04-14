package pl.piwowarski.app.controller;

import io.swagger.v3.oas.annotations.Operation;
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
public class WordController {

    private final WordService wordService;

    @Operation(summary = "Getting text of a given length.",
            description = "Set request param 'length' value to customize text length else length is default 10.")
    @GetMapping
    public ResponseEntity<TextDto> getRandomText(@RequestParam(name = "length", defaultValue = "10") String length) {
        String text = wordService.getRandomText(Integer.parseInt(length));
        TextDto textDto = TextDto.builder().patternText(text).build();
		return new ResponseEntity<>(textDto, HttpStatus.OK);
    }

    @Operation(summary = "Data statistics.",
            description = "Required request body containing: user text, pattern text and time.")
    @PostMapping
    public ResponseEntity<ResultDto> statistics(@RequestBody DataToVerificationDto dataToVerificationDto) {
        ResultDto resultDto = wordService.verification(dataToVerificationDto);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
