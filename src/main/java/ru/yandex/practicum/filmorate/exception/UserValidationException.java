package ru.yandex.practicum.filmorate.exception;

public class UserValidationException extends RuntimeException {
    private final String message;

    public UserValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
