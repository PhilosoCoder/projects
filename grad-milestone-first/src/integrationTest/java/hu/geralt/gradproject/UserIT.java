package hu.geralt.gradproject;

import static hu.geralt.gradproject.TestConstants.NO_RESPONSE;
import static hu.geralt.gradproject.TestConstants.YES_RESPONSE;
import static hu.geralt.gradproject.TestConstants.fortnite;
import static hu.geralt.gradproject.TestConstants.logger;
import static hu.geralt.gradproject.TestConstants.streetsOfRage4;
import static hu.geralt.gradproject.TestConstants.witcher3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import hu.geralt.gradproject.consoles.playstation.PlayStationAccount;
import hu.geralt.gradproject.consoles.playstation.PlaystationConsole;
import hu.geralt.gradproject.consoles.playstation.PlaystationInterface;
import hu.geralt.gradproject.consoles.playstation.PlaystationStore;
import hu.geralt.gradproject.enums.Membership;
import hu.geralt.gradproject.enums.State;
import hu.geralt.gradproject.enums.Type;
import hu.geralt.gradproject.exceptions.DuplicateUsernameException;
import hu.geralt.gradproject.exceptions.InsufficientCreditException;
import hu.geralt.gradproject.general.ConsoleInterface;
import hu.geralt.gradproject.general.User;
import org.junit.jupiter.api.Test;

class UserIT {

    private static final PlaystationStore psStore = new PlaystationStore();
    private static final PlaystationConsole playstation5 = new PlaystationConsole(Type.PLAYSTATION_5, 1);
    private static final PlaystationInterface playstationInterface = new PlaystationInterface(psStore, playstation5);
    private final PlayStationAccount psAccount = new PlayStationAccount(100, Membership.EXTRA);
    private final User psUser = new User("Henry", psAccount, playstationInterface);

    @Test
    void userValidation() {
        assertThrows(DuplicateUsernameException.class, () -> new User("Henry", psAccount, playstationInterface));
    }

    @Test
    void buy() {
        setup();
        assertEquals(3, psUser.getAccount().getGames().size());
        assertEquals("Witcher 3", psUser.getAccount().getGames().get(0).getTitle());
        assertEquals("Streets of Rage 4", psUser.getAccount().getGames().get(1).getTitle());
        assertEquals("Fortnite", psUser.getAccount().getGames().get(2).getTitle());
    }

    private void setup() {
        provideInput(YES_RESPONSE);
        psUser.buy(witcher3);
        provideInput(YES_RESPONSE);
        psUser.buy(streetsOfRage4);
        provideInput(YES_RESPONSE);
        psUser.buy(fortnite);
    }

    private void provideInputs(String... inputs) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String inputConcatenated = String.join(System.lineSeparator(), inputs);
        provideInput(inputConcatenated);
    }

    private void provideInput(String inputConcatenated) {
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream(inputConcatenated.getBytes()));
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

    @Test
    void buyWithoutEnoughCredit() {
        psAccount.setBalance(20);
        provideInput(YES_RESPONSE);
        assertThrows(InsufficientCreditException.class, () -> psUser.buy(witcher3));
    }

    @Test
    void cancellation() {
        setup();
        assertEquals(3, psAccount.getGames().size());
        provideInput(YES_RESPONSE);
        psUser.cancellation(witcher3);
        assertEquals(2, psAccount.getGames().size());
        assertEquals("Streets of Rage 4", psUser.getAccount().getGames().get(0).getTitle());
    }

    @Test
    void pressPowerButton() {
        psUser.pressPowerButton();
        assertEquals(State.ON, playstationInterface.getState());
        psUser.pressPowerButton();
        assertEquals(State.OFF, playstationInterface.getState());
    }

    @Test
    void playGame() {
        setup();
        provideInput("2");
        psUser.playGame();
        assertEquals(streetsOfRage4, playstationInterface.getRunningGame());
    }

    @Test
    void playGameForTheThirdTry() {
        setup();
        provideInputs("5", "0", "1");
        psUser.playGame();
        assertEquals(psAccount.getGames().get(0), playstationInterface.getRunningGame());
    }


    @Test
    void quitGame() {
        setup();
        provideInput("2");
        psUser.playGame();
        assertEquals(psAccount.getGames().get(1), playstationInterface.getRunningGame());
        provideInput("no");
        psUser.quitGame();
        assertNull(playstationInterface.getRunningGame());
    }

    @Test
    void saveGame() {
        setup();
        provideInput("3");
        psUser.playGame();
        provideInput(YES_RESPONSE);
        psUser.saveGame();
        playstation5.getSaves().get(psAccount.getGames().get(2));
        assertTrue(playstation5.getSaves().containsKey(fortnite));
        provideInput(YES_RESPONSE);
        psUser.saveGame();
        assertEquals(2, playstation5.getSaves().get(fortnite).size());
    }

}
