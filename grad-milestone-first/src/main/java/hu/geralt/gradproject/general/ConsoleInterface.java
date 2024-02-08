package hu.geralt.gradproject.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import hu.geralt.gradproject.enums.State;
import hu.geralt.gradproject.exceptions.InsufficientCreditException;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@Setter
public class ConsoleInterface {

    private final Logger logger = LogManager.getLogger();
    private final Scanner scanner = new Scanner(System.in);
    private State state = State.OFF;
    private final Console console;
    private Store store;
    private Game runningGame = null;
    private String gameProgress = "actualProgress";

    public ConsoleInterface(Store store, Console console) {
        this.store = store;
        this.console = console;
    }

    public boolean confirmation(String process) {
        logger.info("Are you sure you want {}?", process);
        String response = scanner.next();
        return Objects.equals(response.toLowerCase(), "yes");
    }

    public void buy(Game game, Account account) {
        if (confirmation("to buy " + game.getTitle())) {
            double gamePrice = store.calculatePrice(account, game);
            if (account.getBalance() < gamePrice) {
                throw new InsufficientCreditException();
            }
            account.addGame(game);
            double newBalance = account.getBalance() - store.calculatePrice(account, game);
            account.setBalance(newBalance);
        }
    }

    public void cancellation(Account account, Game game) {
        if (account.getGames().contains(game) && (confirmation("to delete " + game.getTitle()))) {
            account.removeGame(game);
            logger.info("{} has been removed.", game.getTitle());
        }
    }

    public void pressPowerButton() {
        if (Objects.equals(state, State.OFF)) {
            state = State.ON;
            logger.info(welcomeLogo());
        } else {
            state = State.OFF;
            logger.info("The console has been shut down.");
        }
    }

    private String welcomeLogo() {
        return console.getBrand().toString();
    }

    public void runGame(Account account) {
        int counter = 1;
        logger.info("Your game library contains: ");
        for (Game actualGame : account.getGames()) {
            logger.info("{}. : {}", counter, actualGame.getTitle());
            counter++;
        }
        String numberOfGame = gameChoosing(account);
        Game chosenGame = account.getGames().get(Integer.parseInt(numberOfGame) - 1);
        logger.info("{} has been started.", chosenGame.getTitle());
        runningGame = chosenGame;
    }

    private String gameChoosing(Account account) {
        while (true) {
            logger.info("Press the number of the game you would like to play!");
            String numberOfGame = scanner.next();
            if (isValidGameNumber(numberOfGame, account)) {
                return numberOfGame;
            } else {
                logger.info("Invalid input! Please enter a valid game number.");
            }
        }
    }

    private boolean isValidGameNumber(String input, Account account) {
        try {
            int gameNumber = Integer.parseInt(input);
            return gameNumber > 0 && gameNumber <= account.getGames().size();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void exitGame() {
        if (runningGame != null) {
            logger.info("Would you like to save the game before exit?");
            String response = scanner.next();
            if (response.equalsIgnoreCase("yes")) {
                quickSave();
            }
        }
        runningGame = null;
        logger.info("The game has been closed.");
    }

    public void saveGame() {
        if (confirmation("to save")) {
            quickSave();
        }
    }

    public void quickSave() {
        logger.info("The game has been saved.");
        handleSaveSlots(gameProgress);
    }

    private void handleSaveSlots(String gameProgress) {
        HashMap<Game, List<Save<Progress<String>>>> gameSaves = console.getSaves();
        gameSaves.computeIfAbsent(runningGame, key
                        -> new ArrayList<>())
                .add(new Save<>(runningGame, new Progress<>(gameProgress)));
    }

    public void loadGame() {
        if (!console.getSaves().containsKey(runningGame)) {
            logger.info("You do not have any save yet!");
        } else {
            printSaveSlots();
        }
    }

    private void printSaveSlots() {
        List<Save<Progress<String>>> saves = console.getSaves().get(runningGame);
        logger.info("Saves of {}:", runningGame.getTitle());
        for (Save<Progress<String>> save : saves) {
            logger.info(save);
        }
    }

}
