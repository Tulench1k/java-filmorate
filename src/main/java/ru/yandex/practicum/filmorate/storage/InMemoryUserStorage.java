package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.IncorrectIdException;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final HashMap<Long, User> userStorage;

    public InMemoryUserStorage() {
        this.userStorage = new HashMap<>();
    }

    @Override
    public User add(long id, User user) {
        if (user != null && id > 0 && !userStorage.containsKey(id)) {
            userStorage.put(id, user);
        } else {
            if (id <= 0 || userStorage.containsKey(id)) {
                throw new IncorrectIdException("Некорретный ID.");
            } else {
                throw new UserValidationException("Ошибка валидации.");
            }
        }
        return user;
    }

    @Override
    public User modify(long id, User user) {
        if (user != null && id > 0 && userStorage.containsKey(id)) {
            userStorage.replace(id, user);
        } else {
            if (id <= 0 || !userStorage.containsKey(id)) {
                throw new IncorrectIdException("Некорретный ID.");
            } else {
                throw new UserValidationException("Ошибка валидации.");
            }
        }
        return user;
    }

    @Override
    public void remove(long id) {
        if (id > 0 && userStorage.containsKey(id)) {
            userStorage.remove(id);
        } else {
            throw new IncorrectIdException("Некорретный ID.");
        }
    }

    @Override
    public User get(long id) {
        if (userStorage.containsKey(id)) {
            return userStorage.get(id);
        } else {
            throw new IncorrectIdException("Некорректный ID.");
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userStorage
                .values());
    }
}