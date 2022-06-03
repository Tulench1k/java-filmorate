package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.exception.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Validator {
    public static void validate(Film film) throws FilmValidationException {
        if (film.getName().isEmpty()) {
            throw new EmptyNameException();
        } else if (film.getDescription().length() > 200) {
            throw new TooLongDescriptionException();
        } else if (film.getReleaseDate().isBefore(LocalDate.of(1985, 12, 28))) {
            throw new IncorrectDateException();
        } else if (film.getDuration() < 0) {
            throw new NegativeDurationException();
        }
    }

    public static void validate(User user) throws UserValidationException {
        if (user.getLogin().isBlank()) {
            throw new BlankLoginException();
        } else if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new FutureDateException();
        }
    }
}
