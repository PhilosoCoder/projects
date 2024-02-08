package hu.geralt.gradproject.consoles.playstation;

import java.time.LocalDate;

import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Membership;
import hu.geralt.gradproject.enums.Type;
import hu.geralt.gradproject.general.Console;
import hu.geralt.gradproject.general.ConsoleInterface;
import hu.geralt.gradproject.general.Store;
import hu.geralt.gradproject.general.User;
import org.apache.logging.log4j.Logger;

public class PlaystationInterface extends ConsoleInterface {

    public PlaystationInterface(Store store, Console console) {
        super(store, console);
    }

    public static void main(String[] args) {
        PlaystationStore psStore = new PlaystationStore();
        PlayStationAccount psAccount = new PlayStationAccount(100, Membership.EXTRA);
        PlaystationConsole playstation5 = new PlaystationConsole(Type.PLAYSTATION_5, 1);
        PlaystationInterface playstationInterface = new PlaystationInterface(psStore, playstation5);
        User psUser = new User("Henry", psAccount, playstationInterface);
        PlaystationGame witcher3 = new PlaystationGame(Type.PLAYSTATION_5, "Witcher 3", 44.4,
                4.02, Genre.RPG, LocalDate.of(2023, 6, 14), 40);
        PlaystationGame streetsOfRage4 = new PlaystationGame(Type.PLAYSTATION_4, "Streets of Rage 4", 14.4,
                1.04, Genre.ACTION, LocalDate.of(2022, 5, 7), 30);
        PlaystationGame fortnite = new PlaystationGame(Type.PLAYSTATION_4, "Fortnite", 67.8,
                1.25, Genre.SHOOTER, LocalDate.of(2020, 2, 15), 0);
        Logger logger = playstationInterface.getLogger();

        psUser.pressPowerButton();

        psUser.buy(witcher3);
        psUser.buy(streetsOfRage4);
        psUser.buy(fortnite);

        String genre = "List of games in the selected genre : {}";
        String games = "Games : {}";
        logger.info(games, psUser.getAccount().getGames());
        logger.info("Your balance : {}$", psUser.getAccount().getBalance());
        logger.info("List of games in order by size : {}", psStore.sortBySize(psAccount));
        logger.info("List of games in reverse order by size : {}", psStore.sortBySizeReverseOrder(psAccount));
        logger.info("List of games in order by release date : {}", psStore.sortByDate(psAccount));
        logger.info("List of games in reverse order by release date : {}", psStore.sortByDateReverseOrder(psAccount));
        logger.info(genre, psStore.filterByGenre(psAccount, Genre.ACTION));
        logger.info(genre, psStore.filterByGenre(psAccount, Genre.RPG));
        logger.info(genre, psStore.filterByGenre(psAccount, Genre.PARTY));

        psUser.cancellation(witcher3);

        logger.info(games, psUser.getAccount().getGames());
        psUser.playGame();
        psUser.saveGame();
        psUser.loadGame();
        psUser.quickSave();
        psUser.quitGame();

        psUser.pressPowerButton();
    }
}
