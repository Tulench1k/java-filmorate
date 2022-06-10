package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.exception.IncorrectIdException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final HashMap<Long, Film> filmStorage;

    public InMemoryFilmStorage() {
        this.filmStorage = new HashMap<>();
    }

    @Override
    public Film add(long id, Film film) {
        if (film != null && !filmStorage.containsKey(id)) {
            filmStorage.put(id, film);
        } else {
            if (id <= 0 || filmStorage.containsKey(id)) {
                throw new IncorrectIdException("Некорректный ID.");
            } else {
                throw new FilmValidationException("Ошибка валидации.");
            }
        }
        return film;
    }

    @Override
    public Film modify(long id, Film film) {
        if (film != null && id > 0 && filmStorage.containsKey(id)) {
            filmStorage.replace(id, film);
        } else {
            if (id <= 0 || !filmStorage.containsKey(id)) {
                throw new IncorrectIdException("Некорретный ID.");
            } else {
                throw new FilmValidationException("Ошибка валидации.");
            }
        }
        return film;
    }

    @Override
    public void remove(long id) {
        if (id > 0 && filmStorage.containsKey(id)) {
            filmStorage.remove(id);
        } else {
            throw new IncorrectIdException("Некорректный ID.");
        }
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(filmStorage.values());
    }

    @Override
    public Film get(long id) {
        if (filmStorage.containsKey(id)) {
            return filmStorage.get(id);
        } else {
            throw new IncorrectIdException("Некорректный ID.");
        }
    }
}