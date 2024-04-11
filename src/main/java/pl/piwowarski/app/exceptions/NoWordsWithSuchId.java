package pl.piwowarski.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoWordsWithSuchId extends RuntimeException {

    private final String message;
}
