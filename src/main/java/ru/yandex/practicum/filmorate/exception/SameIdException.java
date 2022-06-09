package ru.yandex.practicum.filmorate.exception;

public class SameIdException extends RuntimeException {
    private final String message;

    public SameIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
