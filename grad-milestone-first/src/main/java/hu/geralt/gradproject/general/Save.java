package hu.geralt.gradproject.general;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Save<T> {

    private final LocalDateTime dateTime;
    private final Game game;
    private T progress;

    public Save(Game game, T progress) {
        this.dateTime = LocalDateTime.now();
        this.game = game;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Save {" +
                "dateTime: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                ", game: " + game +
                ", progress: " + progress +
                '}';
    }
}
