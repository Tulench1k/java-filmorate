package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class User {
    private int id;
    @NonNull @Email private final String email;
    @NonNull private final String login;
    @NonNull private String name;
    @NonNull private final LocalDate birthday;
}
