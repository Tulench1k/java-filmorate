package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.exception.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Validator {
    private final static LocalDate THRESHOLD_DATE = LocalDate
            .of(1895, 12, 28);
    
    public static void validate(Film film) throws FilmValidationException {
        if (film.getName().isEmpty()) {
            throw new EmptyNameException("Пустое имя!");
        } else if (film.getDescription().length() > 200) {
            throw new TooLongDescriptionException("Слишком длинное описание.");
        } else if (film.getReleaseDate().isBefore(THRESHOLD_DATE)) {
            throw new IncorrectDateException("Фильмов в это время еще не снимали.");
        } else if (film.getDuration() < 0) {
            throw new NegativeDurationException("Отрицательная длительность.");
        }
    }

    public static void validate(User user) throws UserValidationException {
        if (user.getLogin().isBlank()) {
            throw new BlankLoginException("Пустой логин.");
        } else if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new FutureDateException("Пользователь еще не успел родиться.");
        }
    }
}
