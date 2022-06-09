package ru.yandex.practicum.filmorate.exception;

public class BlankLoginException extends UserValidationException {
    public BlankLoginException(String s) {
        super(s);
    }
}
