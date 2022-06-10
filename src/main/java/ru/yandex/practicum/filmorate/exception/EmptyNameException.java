package ru.yandex.practicum.filmorate.exception;

public class EmptyNameException extends FilmValidationException {

    public EmptyNameException(String s) {
        super(s);
    }
}
