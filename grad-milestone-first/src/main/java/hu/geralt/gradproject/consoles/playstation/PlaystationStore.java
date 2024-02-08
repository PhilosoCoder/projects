package hu.geralt.gradproject.consoles.playstation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.geralt.gradproject.enums.Genre;
import hu.geralt.gradproject.enums.Membership;
import hu.geralt.gradproject.general.Account;
import hu.geralt.gradproject.general.Game;
import hu.geralt.gradproject.general.Store;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaystationStore implements Store {

    private final Set<Account> accounts = new HashSet<>();

    @Override
    public List<Game> sortByDate(Account account) {
        List<Game> games = new ArrayList<>(account.getGames());
        int numberOfGames = games.size();
        if (numberOfGames < 2) {
            return games;
        }
        List<Game> result = new ArrayList<>();
        while (!games.isEmpty()) {
            Game earliest = findEarliestGame(games);
            result.add(earliest);
            games.remove(earliest);
        }
        return result;
    }

    private Game findEarliestGame(List<Game> games) {
        Game earliest = games.getFirst();
        for (Game game : games) {
            if (game.getReleaseDate().isBefore(earliest.getReleaseDate())) {
                earliest = game;
            }
        }
        return earliest;
    }

    @Override
    public List<Game> sortByDateReverseOrder(Account account) {
        List<Game> games = new ArrayList<>(account.getGames());
        if (games.size() < 2) {
            return games;
        }
        int n = games.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (games.get(j).getReleaseDate().isAfter(games.get(maxIndex).getReleaseDate())) {
                    maxIndex = j;
                }
            }
            Game temp = games.get(maxIndex);
            games.set(maxIndex, games.get(i));
            games.set(i, temp);
        }
        return games;
    }

    @Override
    public List<Game> sortBySize(Account account) {
        List<Game> games = new ArrayList<>(account.getGames());
        //Not using lambda intentionally at this phase
        Comparator<Game> sizeComparator = new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return Double.compare(o1.getSize(), o2.getSize());
            }
        };
        games.sort(sizeComparator);
        return games;
    }

    @Override
    public List<Game> sortBySizeReverseOrder(Account account) {
        List<Game> games = new ArrayList<>(account.getGames());
        Comparator<Game> sizeComparator = Comparator.comparingDouble(Game::getSize);
        games.sort(sizeComparator.reversed());
        return games;
    }

    @Override
    public List<Game> filterByGenre(Account account, Genre genre) {
        List<Game> games = account.getGames();
        List<Game> result = new ArrayList<>();
        for (Game game : games) {
            if (game.getGenre() == genre) {
                result.add(game);
            }
        }
        return result;
    }

    @Override
    public double calculatePrice(Account account, Game game) {
        if (account.getMembership() == Membership.BASIC) {
            return game.getPrice();
        }
        double discount = account.getMembership().getDiscount() / 100.0;
        return game.getPrice() * (1 - discount);
    }

}
