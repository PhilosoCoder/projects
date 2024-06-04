package codekata.kata04datamunging;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DataMungingTest {

    private final DataMunging dataMunging = new DataMunging();

    @Test
    void testFindSmallestTemperatureSpreadDay() throws IOException {
        String weatherFilePath = "src/main/resources/weather.dat";
        int result = dataMunging.findSmallestTemperatureSpreadDay(weatherFilePath);
        assertEquals(14, result);
    }

    @Test
    void testFindSmallestDifferenceInGoalsTeam() throws IOException {
        String footballFilePath = "src/main/resources/football.dat";
        String result = dataMunging.findSmallestDifferenceInGoalsTeam(footballFilePath);
        assertEquals("Aston Villa", result);
    }
}
