package br.com.mobi.codeinterview.unit.adapter;

import br.com.mobi.codeinterview.adapter.PointOfInterestCsvAdapter;
import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointOfInterestCsvAdapterTest {

    private final PointOfInterestCsvAdapter adapter = new PointOfInterestCsvAdapter();

    @Test
    void pointOfInterestCsvAdapterTest_ConvertingSuccessfully(){
        List<String[]> csvFileMock = getCsvMockedValues();
        List<PointOfInterest> pointsOfInterest = adapter.toEntity(csvFileMock);

        csvFileMock.forEach(csvLine -> {
            String name = csvLine[0];
            PointOfInterest pointOfInterest = pointsOfInterest.stream()
                    .filter(poi -> poi.getName().equalsIgnoreCase(name)).findFirst().get();

            assertEquals(csvLine[0], pointOfInterest.getName());
            assertEquals(Integer.valueOf(csvLine[1]), pointOfInterest.getRadius());
            assertEquals(Double.valueOf(csvLine[2]), pointOfInterest.getLatitude());
            assertEquals(Double.valueOf(csvLine[3]), pointOfInterest.getLongitude());
        });
    }

    private List<String[]> getCsvMockedValues() {
        return Arrays.asList(
                new String[]{"POINT 01","300","-25.56742701740896","-51.47653363645077"},
                new String[]{"POINT 02","120","-90","-180"},
                new String[]{"POINT 03","240","90","180"},
                new String[]{"POINT 04","360","0","0"});
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null,300,-25.56742701740896,-51.47653363645077",
            "POINT 01,null,-25.56742701740896,-51.47653363645077",
            "POINT 01,300,null,-51.47653363645077",
            "POINT 01,300,-25.56742701740896,null"
    }, nullValues = {"null"})
    void pointOfInterestCsvAdapterTest_NullValues(String name, String radius, String latitude, String longitude) {
        List<String[]> csvLines = Collections.singletonList(new String[]{name, radius, latitude, longitude});
        List<PointOfInterest> pointsOfInterest = adapter.toEntity(csvLines);

        assertNotNull(pointsOfInterest);
        assertTrue(pointsOfInterest.isEmpty());
    }

}
