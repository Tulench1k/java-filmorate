package ru.yandex.practicum.filmorate.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.IncorrectIdException;
import ru.yandex.practicum.filmorate.exception.SameIdException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {
    private final UserStorage userStorage;
    private int globalId;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
        this.globalId = 0;
    }

    public List<User> getUsers() {
        return userStorage.getAll();
    }

    public User createUser(User user) {
        Validator.validate(user);
        long id = getId();
        user.setId(id);
        if (user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return userStorage.add(id, user);
    }

    public User updateUser(User user) {
        Validator.validate(user);
        if (user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return userStorage.modify(user.getId(), user);
    }

    public User addFriend(long userId, long friendId) {
        if (userId == friendId) {
            throw new SameIdException("Нельзя добавить в друзья самого себя.");
        } else if (!userStorage.getAll().contains(userStorage.get(friendId))) {
            throw new IncorrectIdException("Некорректный ID.");
        } else {
            userStorage
                    .get(userId)
                    .getFriends()
                    .add(friendId);
            userStorage
                    .get(friendId)
                    .getFriends()
                    .add(userId);
            return userStorage.get(userId);
        }
    }

    public User removeFriend(long userId, long friendId) {
        if (userStorage
                .get(userId)
                .getFriends()
                .contains(friendId)) {
            userStorage
                    .get(userId)
                    .getFriends()
                    .remove(friendId);
            userStorage
                    .get(friendId)
                    .getFriends()
                    .remove(userId);
        } else {
            throw new IncorrectIdException("Некорректный ID.");
        }

        return  userStorage.get(userId);
    }
    public List<User> getFriends(long userId) {
        List<User> friendList = new ArrayList<>();
        userStorage
                .get(userId)
                .getFriends()
                .forEach(id -> friendList
                        .add(userStorage.get(id)));
        return friendList;
    }

    public List<User> getCommonFriends(long userId, long friendId) {
        List<User> commonFriends = new ArrayList<>();
        userStorage
                .get(userId)
                .getFriends()
                .stream()
                .filter(id -> userStorage
                        .get(friendId)
                        .getFriends()
                        .contains(id))
                .forEach(id -> commonFriends
                        .add(userStorage.get(id)));
        return commonFriends;
    }

    public User getById(long id) {
        return userStorage.get(id);
    }
    private long getId() {
        ++globalId;
        return globalId;
    }
}