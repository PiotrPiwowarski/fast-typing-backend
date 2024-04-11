package pl.piwowarski.app.exceptions;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ErrorMessages {

    public final static String NO_WORDS_WITH_SUCH_ID = "Brak słów o podanym id";
}
