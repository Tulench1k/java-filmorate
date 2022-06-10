package ru.yandex.practicum.filmorate.exception;

public class NegativeDurationException extends FilmValidationException {
    public NegativeDurationException(String s) {
        super(s);
    }
}
