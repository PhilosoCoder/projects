package codewars.humanreadabletime;

import java.time.Duration;

public class HumanReadableTime {

    public static String makeReadable(int seconds) {
        Duration duration = Duration.ofSeconds(seconds);

        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secondsPart = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, secondsPart);
    }
}