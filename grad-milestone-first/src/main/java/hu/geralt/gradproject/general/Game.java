package hu.geralt.gradproject.general;

import java.time.LocalDate;

import hu.geralt.gradproject.enums.Brand;
import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Type;
import lombok.Data;

@Data
public abstract class Game {

    private final Brand brand;
    private final Type type;
    private final String title;
    private double size;
    private double version;
    private final Genre genre;
    private final LocalDate releaseDate;
    private double price;

    protected Game(Type type, String title, double size, double version, Genre genre, LocalDate releaseDate, double price) {
        this.brand = Console.setBrand(type);
        this.type = type;
        this.title = title;
        this.size = size;
        this.version = version;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    protected Game(Brand brand, Type type, String title, Genre genre, LocalDate releaseDate) {
        this.brand = brand;
        this.type = type;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }
}
