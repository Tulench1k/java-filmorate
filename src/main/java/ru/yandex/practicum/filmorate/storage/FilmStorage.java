package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film add(long id, Film film);

    Film modify(long id, Film film);

    void remove(long id);

    List<Film> getAll();

    Film get(long id);
}
