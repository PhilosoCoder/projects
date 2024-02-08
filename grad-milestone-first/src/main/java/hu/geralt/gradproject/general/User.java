package hu.geralt.gradproject.general;

import hu.geralt.gradproject.exceptions.DuplicateUsernameException;
import lombok.Data;

@Data
public class User{

    private String username;
    private Account account;
    private ConsoleInterface consoleInterface;

    public User(String username, Account account, ConsoleInterface consoleInterface) {
        this.username = username;
        this.account = account;
        this.consoleInterface = consoleInterface;
        addUserAccountToStore();
    }

    private void addUserAccountToStore() {
        validateUsername();
        consoleInterface.getStore().getAccounts().add(this.getAccount());
    }

    private void validateUsername() {
        if (consoleInterface.getStore().getAccounts().contains(this.getAccount())) {
            throw new DuplicateUsernameException(username);
        }
    }

    public void buy(Game game) {
        consoleInterface.buy(game, account);
    }

    public void cancellation(Game game) {
        consoleInterface.cancellation(account, game);
    }

    public void pressPowerButton() {
        consoleInterface.pressPowerButton();
    }

    public void playGame() {
        consoleInterface.runGame(account);
    }

    public void quitGame() {
        consoleInterface.exitGame();
    }

    public void saveGame() {
        consoleInterface.saveGame();
    }

    public void quickSave() {
        consoleInterface.quickSave();
    }

    public void loadGame() {
        consoleInterface.loadGame();
    }

}
