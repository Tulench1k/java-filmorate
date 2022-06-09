package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<Film> getFilms(HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());
        return filmService.getFilms();
    }

    @PostMapping(value = "/films")
    public Film postFilm(@Valid @RequestBody Film film,
                         HttpServletRequest request) throws FilmValidationException {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        return filmService.postFilm(film);
    }

    @PutMapping(value = "/films")
    public Film putFilm(@Valid @RequestBody Film film,
                        HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        return filmService.putFilm(film);
    }

    @GetMapping(value = "/films/{filmId}")
    public Film get(@PathVariable long filmId,
                    HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        return filmService.getById(filmId);
    }

    @PutMapping(value = "/films/{filmId}/like/{userId}")
    public Film addLike(@PathVariable long filmId,
                        @PathVariable long userId,
                        HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        return filmService.addLike(userId, filmId);
    }

    @DeleteMapping(value = "/films/{filmId}/like/{userId}")
    public Film removeLike(@PathVariable long filmId,
                           @PathVariable long userId,
                           HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        return filmService.removeLike(userId, filmId);
    }

    @GetMapping(value = "/films/popular")
    public List<Film> getTop(@RequestParam(required = false, defaultValue = "10") Long count,
                             HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        return filmService.getTop(count);
    }
}