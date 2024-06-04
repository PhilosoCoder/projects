package codewars.humanreadabledurationformat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeFormatter {
    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return "now";
        }

        Map<String, Integer> dates = getDates(seconds);
        Map<String, Integer> validDates = filterValidDates(dates);

        String[] units = {"year", "day", "hour", "minute", "second"};
        StringBuilder sb = new StringBuilder();
        int timeCounter = 0;

        for (String unit : units) {
            if (validDates.containsKey(unit)) {
                int value = validDates.get(unit);
                appendUnit(sb, value, unit);
                timeCounter++;
                appendSeparator(sb, timeCounter, validDates.size());
            }
        }

        return sb.toString();
    }

    private static Map<String, Integer> filterValidDates(Map<String, Integer> dates) {
        return dates.entrySet().stream()
                .filter(entry -> entry.getValue() >= 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static void appendUnit(StringBuilder sb, int value, String unit) {
        sb.append(value).append(" ").append(unit);
        if (value != 1) {
            sb.append("s");
        }
    }

    private static void appendSeparator(StringBuilder sb, int timeCounter, int totalUnits) {
        if (totalUnits > 1 && timeCounter != totalUnits) {
            if (timeCounter != totalUnits - 1) {
                sb.append(", ");
            } else {
                sb.append(" and ");
            }
        }
    }

    private static Map<String, Integer> getDates(int seconds) {
        int second = (((seconds % 604800) % 86400) % 3600) % 60;
        int minutes = (((seconds % 604800) % 86400) % 3600) / 60;
        int hours = ((seconds % 604800) % 86400) / 3600;
        int days = (seconds / 86400) % 365;
        int years = (seconds / (86400 * 365));

        Map<String, Integer> dates = new HashMap<>();
        dates.put("year", years);
        dates.put("day", days);
        dates.put("hour", hours);
        dates.put("minute", minutes);
        dates.put("second", second);
        return dates;
    }

}
