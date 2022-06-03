package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.IncorrectIdException;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
public class UserController {
    private int maxId = 0;

    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public Collection<User> getUsers(HttpServletRequest request) {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());
        return users.values();
    }

    @PostMapping(value = "/users")
    public User createUser(@Valid @RequestBody User user,
                           HttpServletRequest request) throws UserValidationException {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());
        Validator.validate(user);
        int id = getId();
        user.setId(id);
        if (user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(id, user);
        return user;
    }

    @PutMapping(value = "/users")
    User updateUser(@Valid @RequestBody User user,
                    HttpServletRequest request) throws
            IncorrectIdException, UserValidationException {
        log.info("Получен запрос к эндпоинту: '{} {}'",
                request.getMethod(), request.getRequestURI());

        Validator.validate(user);
        if (user.getId() <= 0) {
            throw new IncorrectIdException();
        } else {
            if (user.getName().isBlank()) {
                user.setName(user.getLogin());
            }
            users.put(user.getId(), user);
            return user;
        }
    }

    private int getId() {
        ++maxId;
        return maxId;
    }
}