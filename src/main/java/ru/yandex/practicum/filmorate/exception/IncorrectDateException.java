package ru.yandex.practicum.filmorate.exception;

public class IncorrectDateException extends FilmValidationException {
    public IncorrectDateException(String s) {
        super(s);
    }
}
