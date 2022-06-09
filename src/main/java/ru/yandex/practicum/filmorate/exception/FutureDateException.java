package ru.yandex.practicum.filmorate.exception;

public class FutureDateException extends UserValidationException {
    public FutureDateException(String s) {
        super(s);
    }
}
