package codekata.kata04datamunging;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataMunging {

    private static final Logger logger = Logger.getLogger(DataMunging.class.getName());

    public static void main(String[] args) {
        DataMunging dataMunging = new DataMunging();

        String weatherFilePath = "src/main/resources/weather.dat";
        try {
            int smallestSpreadDay = dataMunging.findSmallestTemperatureSpreadDay(weatherFilePath);
            logger.info("Day with the smallest temperature spread: " + smallestSpreadDay);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred:", e);
        }

        String footballFilePath = "src/main/resources/football.dat";
        try {
            String smallestDifferenceInGoalsTeam = dataMunging.findSmallestDifferenceInGoalsTeam(footballFilePath);
            logger.info("Team with the smallest difference in goals: " + smallestDifferenceInGoalsTeam);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred:", e);
        }
    }

    public int findSmallestTemperatureSpreadDay(String filePath) throws IOException {
        try (BufferedReader br = createBufferedReader(filePath)) {
            String line;
            int smallestSpreadDay = 0;
            int smallestSpread = Integer.MAX_VALUE;

            for (int i = 0; i < 2; i++) {
                br.readLine();
            }

            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("mo")) {
                    continue;
                }

                String[] columns = line.trim().split("\\s+");

                int day = Integer.parseInt(columns[0]);
                int maxTemp = Integer.parseInt(columns[1].replace("*", ""));
                int minTemp = Integer.parseInt(columns[2].replace("*", ""));

                int temperatureSpread = maxTemp - minTemp;

                if (temperatureSpread < smallestSpread) {
                    smallestSpread = temperatureSpread;
                    smallestSpreadDay = day;
                }
            }
            return smallestSpreadDay;
        }
    }

    public String findSmallestDifferenceInGoalsTeam(String filePath) throws IOException {
        try (BufferedReader br = createBufferedReader(filePath)) {
            String line;
            String smallestDifferenceInGoalsTeam = "";
            int smallestDifferenceInGoals = Integer.MAX_VALUE;

            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("-")) {
                    continue;
                }

                String[] columns = line.trim().split("\\s+");

                String team = line.substring(7, 23).trim().replace("_", " ");
                int goalScored = Integer.parseInt(columns[6]);
                int goalAgainst = Integer.parseInt(columns[8]);

                int goalDifference = Math.abs(goalScored - goalAgainst);

                if (goalDifference < smallestDifferenceInGoals) {
                    smallestDifferenceInGoals = goalDifference;
                    smallestDifferenceInGoalsTeam = team;
                }
            }
            return smallestDifferenceInGoalsTeam;
        }
    }

    private BufferedReader createBufferedReader(String filePath) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }

}
