package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.exception.IncorrectIdException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    private int maxId = 0;

    @GetMapping("/films")
    public Collection<Film> getFilms(HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());
        return films.values();
    }

    @PostMapping(value = "/films")
    public Film postFilm(@Valid @RequestBody Film film,
                         HttpServletRequest request) throws FilmValidationException {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        Validator.validate(film);
        int id = getId();
        film.setId(id);
        films.put(id, film);
        return film;
    }

    @PutMapping(value = "/films")
    public Film putFilm(@Valid @RequestBody Film film,
                        HttpServletRequest request) throws
            IncorrectIdException, FilmValidationException {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        Validator.validate(film);
        if (film.getId() <= 0) {
            throw new IncorrectIdException();
        } else {
            films.put(film.getId(), film);
            return film;
        }
    }

    private int getId() {
        ++maxId;
        return maxId;
    }
}
