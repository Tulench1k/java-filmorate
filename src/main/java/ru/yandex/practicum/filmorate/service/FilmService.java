package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.IncorrectIdException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.validator.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private int globalId;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
        this.globalId = 0;
    }

    public List<Film> getFilms() {
        return filmStorage.getAll();
    }

    public Film postFilm(Film film) {
        Validator.validate(film);
        long id = getId();
        film.setId(id);
        return filmStorage.add(id, film);
    }

    public Film putFilm(Film film) {
        Validator.validate(film);
        return filmStorage.modify(film.getId(), film);
    }

    public Film addLike(long userId, long filmId) {
        filmStorage
                .get(filmId)
                .getLikesIds()
                .add(userId);
        return filmStorage.get(filmId);
    }

    public Film removeLike(long userId, long filmId) {
        if (filmStorage
                .get(filmId)
                .getLikesIds()
                .contains(userId)) {
            filmStorage
                    .get(filmId)
                    .getLikesIds()
                    .remove(userId);
            return  filmStorage.get(filmId);
        } else {
            throw new IncorrectIdException("Некорректный ID.");
        }
    }
    public List<Film> getTop(long size) {
        return filmStorage
                .getAll()
                .stream()
                .sorted(Comparator.comparingInt(o -> -o.getLikesIds().size()))
                .limit(size)
                .collect(Collectors.toList());
    }

    public Film getById(long id) {
        return filmStorage.get(id);
    }
    private long getId() {
        ++globalId;
        return globalId;
    }
}
