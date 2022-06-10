package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User add(long id, User user);

    User modify(long id, User user);

    void remove(long id);

    User get(long id);

    List<User> getAll();
}
