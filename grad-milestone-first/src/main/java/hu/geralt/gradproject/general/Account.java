package hu.geralt.gradproject.general;

import java.util.ArrayList;
import java.util.List;

import hu.geralt.gradproject.enums.Membership;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {

    private double balance;
    private Membership membership;
    private List<Game> games;

    protected Account(double balance, Membership membership) {
        this.balance = balance;
        this.membership = membership;
        games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

}
