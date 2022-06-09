package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    private long id;
    
    @NonNull 
    private final String name;
    
    @NonNull 
    private final String description;
    
    @NonNull
    private final LocalDate releaseDate;
    
    private final int duration;

    private Set<Long> likesIds = new HashSet<>();
}
