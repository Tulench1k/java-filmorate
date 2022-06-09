package ru.yandex.practicum.filmorate.exception;

public class IncorrectEmailException extends RuntimeException {
    private final String message;

    public IncorrectEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
