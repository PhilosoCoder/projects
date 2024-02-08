package hu.geralt.gradproject;

import static hu.geralt.gradproject.TestConstants.NO_RESPONSE;
import static hu.geralt.gradproject.TestConstants.YES_RESPONSE;
import static hu.geralt.gradproject.TestConstants.fortnite;
import static hu.geralt.gradproject.TestConstants.logger;
import static hu.geralt.gradproject.TestConstants.streetsOfRage4;
import static hu.geralt.gradproject.TestConstants.witcher3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import hu.geralt.gradproject.consoles.playstation.PlayStationAccount;
import hu.geralt.gradproject.consoles.playstation.PlaystationConsole;
import hu.geralt.gradproject.consoles.playstation.PlaystationInterface;
import hu.geralt.gradproject.consoles.playstation.PlaystationStore;
import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Membership;
import hu.geralt.gradproject.enums.Type;
import hu.geralt.gradproject.general.ConsoleInterface;
import hu.geralt.gradproject.general.Game;
import hu.geralt.gradproject.general.User;
import org.junit.jupiter.api.Test;

class PlayStationStoreIT {

    private static final PlaystationStore store = new PlaystationStore();
    private final PlayStationAccount account = new PlayStationAccount(100, Membership.EXTRA);
    private static final PlaystationConsole playstation5 = new PlaystationConsole(Type.PLAYSTATION_5, 1);
    private static final PlaystationInterface playstationInterface = new PlaystationInterface(store, playstation5);
    private final User user = new User("Henry", account, playstationInterface);

    private void provideInput(String data) {
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        try {
            Field scannerField = ConsoleInterface.class.getDeclaredField("scanner");
            scannerField.setAccessible(true);
            scannerField.set(playstationInterface, scanner);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.info(e);
        }
        System.setIn(sysInBackup);
    }

    @Test
    void givenConfirmationWhenResponseIsYes() {
        provideInput(YES_RESPONSE);
        assertTrue(playstationInterface.confirmation("perform the action"));
    }

    @Test
    void givenConfirmationWhenResponseIsNo() {
        provideInput(NO_RESPONSE);
        assertFalse(playstationInterface.confirmation("perform the action"));
    }

    private void setup() {
        provideInput(YES_RESPONSE);
        user.buy(witcher3);
        provideInput(YES_RESPONSE);
        user.buy(streetsOfRage4);
        provideInput(YES_RESPONSE);
        user.buy(fortnite);
    }
    @Test
    void sortByDate() {
        setup();
        List<Game> result = Arrays.asList(fortnite, streetsOfRage4, witcher3);
        assertEquals(result, store.sortByDate(account));
    }

    @Test
    void sortByDateReverseOrder() {
        setup();
        List<Game> result = Arrays.asList(witcher3, streetsOfRage4, fortnite);
        assertEquals(result, store.sortByDateReverseOrder(account));
    }

    @Test
    void sortBySize() {
        setup();
        List<Game> result = Arrays.asList(streetsOfRage4, witcher3, fortnite);
        assertEquals(result, store.sortBySize(account));
    }

    @Test
    void sortBySizeReverseOrder() {
        setup();
        List<Game> result = Arrays.asList(fortnite, witcher3, streetsOfRage4);
        assertEquals(result, store.sortBySizeReverseOrder(account));
    }

    @Test
    void filterByGenre() {
        setup();
        assertEquals(List.of(witcher3), store.filterByGenre(account, Genre.RPG));
        assertEquals(List.of(streetsOfRage4), store.filterByGenre(account, Genre.ACTION));
        assertEquals(List.of(fortnite), store.filterByGenre(account, Genre.SHOOTER));
    }

}
