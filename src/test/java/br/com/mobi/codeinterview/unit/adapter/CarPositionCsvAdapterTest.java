package br.com.mobi.codeinterview.unit.adapter;

import br.com.mobi.codeinterview.adapter.CarPositionCsvAdapter;
import br.com.mobi.codeinterview.database.entity.CarPosition;
import br.com.mobi.codeinterview.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarPositionCsvAdapterTest {

    private final CarPositionCsvAdapter adapter = new CarPositionCsvAdapter();

    @Test
    void carPositionCsvAdapterTest_ConvertingSuccessfully() {
        List<String[]> csvFileMock = getCsvMockedValues();
        List<CarPosition> carPositions = adapter.toEntity(csvFileMock);

        csvFileMock.forEach(csvLine -> {
            String plate = csvLine[0];
            CarPosition carPosition = carPositions.stream()
                    .filter(car -> car.getPlate().equalsIgnoreCase(plate)).findFirst().get();

            String trimmedDate = csvLine[1]
                    .substring(4, csvLine[1].indexOf("("))
                    .replace("GMT", "")
                    .trim();

            assertEquals(csvLine[0], carPosition.getPlate());
            assertEquals(DateUtils.fromString(trimmedDate, "MMM dd uuuu HH:mm:ss ZZZ"), carPosition.getPositionDate());
            assertEquals(Integer.valueOf(csvLine[2]), carPosition.getSpeed());
            assertEquals(Double.valueOf(csvLine[3]), carPosition.getLongitude());
            assertEquals(Double.valueOf(csvLine[4]), carPosition.getLatitude());
            assertEquals(Boolean.valueOf(csvLine[5]), carPosition.getIgnition());
        });
    }

    private List<String[]> getCsvMockedValues() {
        return Arrays.asList(
                new String[]{"TESTE001", "Wed Jan 18 1998 00:04:03 GMT-0200 (Hora oficial do Brasil)","0","-51.469891","-25.3649141","false"},
                new String[]{"TESTE002", "Wed Apr 28 2004 09:24:19 GMT-0200 (Hora oficial do Brasil)","120","-180","-90","true"},
                new String[]{"TESTE003", "Wed Sep 19 2012 14:41:32 GMT-0200 (Hora oficial do Brasil)","240","180","90","false"},
                new String[]{"TESTE004", "Wed Dec 31 2023 23:58:51 GMT-0200 (Hora oficial do Brasil)","360","0","0","true"});
    }

    @Test
    void carPositionCsvAdapterTest_PlateIsNull() {
        String[] position = {null, "Wed Jan 18 1998 00:04:03 GMT-0200 (Hora oficial do Brasil)","0","-51.469891","-25.3649141","false"};
        List<CarPosition> carPositions = adapter.toEntity(Collections.singletonList(position));
        assertNotNull(carPositions);
        assertTrue(carPositions.isEmpty());
    }

    @Test
    void carPositionCsvAdapterTest_PositionDateIsNull() {
        String[] position = {"TESTE001", null,"0","-51.469891","-25.3649141","false"};
        List<CarPosition> carPositions = adapter.toEntity(Collections.singletonList(position));
        assertNotNull(carPositions);
        assertTrue(carPositions.isEmpty());
    }

    @Test
    void carPositionCsvAdapterTest_SpeedIsNull() {
        String[] position = {"TESTE001", "Wed Jan 18 1998 00:04:03 GMT-0200 (Hora oficial do Brasil)",null,"-51.469891","-25.3649141","false"};
        List<String[]> csvLines = Collections.singletonList(position);

        assertThrows(NumberFormatException.class, () -> adapter.toEntity(csvLines));
    }

    @Test
    void carPositionCsvAdapterTest_LongitudeIsNull() {
        String[] position = {"TESTE001", "Wed Jan 18 1998 00:04:03 GMT-0200 (Hora oficial do Brasil)","0",null,"-25.3649141","false"};
        List<String[]> csvLines = Collections.singletonList(position);

        assertThrows(NullPointerException.class, () -> adapter.toEntity(csvLines));
    }

    @Test
    void carPositionCsvAdapterTest_LatitudeIsNull() {
        String[] position = {"TESTE001", "Wed Jan 18 1998 00:04:03 GMT-0200 (Hora oficial do Brasil)","0","-51.469891",null,"false"};
        List<String[]> csvLines = Collections.singletonList(position);

        assertThrows(NullPointerException.class, () -> adapter.toEntity(csvLines));
    }

    @Test
    void carPositionCsvAdapterTest_IgnitionIsNull() {
        String[] position = {"TESTE001", "Wed Jan 18 1998 00:04:03 GMT-0200 (Hora oficial do Brasil)","0","-51.469891","-25.3649141",null};
        List<CarPosition> carPositions = adapter.toEntity(Collections.singletonList(position));

        assertNotNull(carPositions);
        assertEquals(1, carPositions.size());
        assertFalse(carPositions.get(0).getIgnition());
    }

}
