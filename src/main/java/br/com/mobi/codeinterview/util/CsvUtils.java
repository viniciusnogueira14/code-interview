package br.com.mobi.codeinterview.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CsvUtils {

    public static List<String[]> readAll(Path csvPath) throws Exception {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        try (Reader reader = Files.newBufferedReader(csvPath)) {
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser);

            try (CSVReader csvReader = csvReaderBuilder.build()) {
                return csvReader.readAll();
            }
        }
    }
}
