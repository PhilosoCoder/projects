package hu.geralt.gradproject.consoles.playstation;

import java.time.LocalDate;

import hu.geralt.gradproject.enums.Brand;
import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Type;
import hu.geralt.gradproject.general.Game;

public class PlaystationGame extends Game {

    public PlaystationGame(Type type, String title, double size, double version, Genre genre, LocalDate releaseDate, double price) {
        super(type, title, size, version, genre, releaseDate, price);
    }

    public PlaystationGame(Brand brand, Type type, String title, Genre genre, LocalDate releaseDate) {
        super(brand, type, title, genre, releaseDate);
    }

}
