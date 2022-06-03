package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class Film {
    private int id;
    @NonNull private final String name;
    @NonNull private final String description;
    @NonNull private final LocalDate releaseDate;
    private final int duration;
}
