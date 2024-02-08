package hu.geralt.gradproject.consoles.playstation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Membership;
import hu.geralt.gradproject.enums.Type;
import org.junit.jupiter.api.Test;

class PlaystationStoreTest {

    @Test
    void calculatePrice() {
        PlaystationStore store = new PlaystationStore();
        PlayStationAccount account = new PlayStationAccount(100, Membership.EXTRA);
        PlaystationGame witcher3 = new PlaystationGame(Type.PLAYSTATION_5, "Witcher 3", 44.4,
                4.02, Genre.RPG, LocalDate.of(2023, 6, 14), 40);
        PlaystationGame streetsOfRage4 = new PlaystationGame(Type.PLAYSTATION_4, "Streets of Rage 4", 14.4,
                1.04, Genre.ACTION, LocalDate.of(2022, 5, 7), 30);
        PlaystationGame fortnite = new PlaystationGame(Type.PLAYSTATION_4, "Fortnite", 67.8,
                1.25, Genre.SHOOTER, LocalDate.of(2020, 2, 15), 0);
        assertEquals(36, store.calculatePrice(account, witcher3));
        assertEquals(0, store.calculatePrice(account, fortnite));
        assertEquals(27, store.calculatePrice(account, streetsOfRage4));
    }

}