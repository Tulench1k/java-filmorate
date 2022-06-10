package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private long id;

    private Set<Long> friends = new HashSet<>();
    
    @NonNull 
    @Email
    private final String email;
    
    @NonNull
    private final String login;
    
    @NonNull 
    private String name;
    
    @NonNull 
    private final LocalDate birthday;
}
