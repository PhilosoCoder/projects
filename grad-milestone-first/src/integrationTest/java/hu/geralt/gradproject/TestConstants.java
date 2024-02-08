package hu.geralt.gradproject;

import java.time.LocalDate;

import hu.geralt.gradproject.consoles.playstation.PlaystationGame;
import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestConstants {

    static final String YES_RESPONSE = "yes";
    static final String NO_RESPONSE = "no";
    static final Logger logger = LogManager.getLogger();
    static final PlaystationGame witcher3 = new PlaystationGame(Type.PLAYSTATION_5, "Witcher 3", 44.4,
            4.02, Genre.RPG, LocalDate.of(2023, 6, 14), 40);
    static final PlaystationGame streetsOfRage4 = new PlaystationGame(Type.PLAYSTATION_4, "Streets of Rage 4", 14.4,
            1.04, Genre.ACTION, LocalDate.of(2022, 5, 7), 30);
    static final PlaystationGame fortnite = new PlaystationGame( Type.PLAYSTATION_4, "Fortnite", 67.8,
            1.25, Genre.SHOOTER, LocalDate.of(2020, 2, 15), 0);

}
