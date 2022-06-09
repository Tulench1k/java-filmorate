package ru.yandex.practicum.filmorate.exception;

public class IncorrectIdException extends RuntimeException {
    private final String message;

    public IncorrectIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
