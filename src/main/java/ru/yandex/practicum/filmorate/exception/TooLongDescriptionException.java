package ru.yandex.practicum.filmorate.exception;

public class TooLongDescriptionException extends FilmValidationException {
    public TooLongDescriptionException(String s) {
        super(s);
    }
}
