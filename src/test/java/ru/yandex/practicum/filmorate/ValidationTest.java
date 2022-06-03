package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exception.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.Validator;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Random;

@SpringBootTest
public class ValidationTest {

    @Test
    public void emptyLoginTest() {
        User user = new User(
                "email@yandex.ru",
                "",
                "Name",
                LocalDate.of(2001, 11, 16));

        BlankLoginException e = Assertions.assertThrows(
                BlankLoginException.class,
                () -> Validator.validate(user));
        Assertions.assertEquals(BlankLoginException.class, e.getClass());
    }

    @Test
    public void blankLoginTest() {
        User user = new User(
                "email@yandex.ru",
                "   ",
                "Name",
                LocalDate.of(2001, 11, 16));

        BlankLoginException e = Assertions.assertThrows(
                BlankLoginException.class,
                () -> Validator.validate(user));
        Assertions.assertEquals(BlankLoginException.class, e.getClass());
    }

    @Test
    public void futureDateException() {
        User user = new User(
                "email@yandex.ru",
                "login",
                "Name",
                LocalDate.of(2101, 11, 16));

        FutureDateException e = Assertions.assertThrows(
                FutureDateException.class,
                () -> Validator.validate(user));
        Assertions.assertEquals(FutureDateException.class, e.getClass());
    }

    @Test
    public void emptyNameTest() {
        Film film = new Film("",
                "description",
                LocalDate.of(2001, 12, 15),
                90);

        EmptyNameException e = Assertions.assertThrows(
                EmptyNameException.class,
                () -> Validator.validate(film));
        Assertions.assertEquals(EmptyNameException.class, e.getClass());
    }

    @Test
    public void tooLongDescriptionTest() {
        byte[] array = new byte[211];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);

        System.out.println(generatedString);

        Film film = new Film(
                "name",
                generatedString,
                LocalDate.of(2001, 12, 15),
                90);

        System.out.println(film.getDescription().length());

        TooLongDescriptionException e = Assertions.assertThrows(
                TooLongDescriptionException.class,
                () -> Validator.validate(film));
        Assertions.assertEquals(TooLongDescriptionException.class, e.getClass());
    }

    @Test
    public void negativeDurationException() {
        Film film = new Film(
                "name",
                "description",
                LocalDate.of(2001, 12, 15),
                -90);

        NegativeDurationException e = Assertions.assertThrows(
                NegativeDurationException.class,
                () -> Validator.validate(film));
        Assertions.assertEquals(NegativeDurationException.class, e.getClass());
    }

    @Test
    public void incorrectDateException() {
        Film film = new Film(
                "name",
                "description",
                LocalDate.of(1985, 12, 27),
                90);

        IncorrectDateException e = Assertions.assertThrows(
                IncorrectDateException.class,
                () -> Validator.validate(film));
        Assertions.assertEquals(IncorrectDateException.class, e.getClass());
    }
}
