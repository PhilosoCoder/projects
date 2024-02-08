package hu.geralt.gradproject.general;

import java.util.List;
import java.util.Set;

import hu.geralt.gradproject.enums.Genre;

public interface Store {

    List<Game> sortBySize(Account account);
    List<Game> sortBySizeReverseOrder(Account account);
    List<Game> sortByDate(Account account);
    List<Game> sortByDateReverseOrder(Account account);
    List<Game> filterByGenre(Account account, Genre genre);
    double calculatePrice(Account account, Game game);
    Set<Account> getAccounts();

}
