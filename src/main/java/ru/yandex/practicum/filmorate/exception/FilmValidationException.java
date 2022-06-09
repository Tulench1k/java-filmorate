package ru.yandex.practicum.filmorate.exception;

public class FilmValidationException extends RuntimeException {
    private final String message;
    public FilmValidationException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
