package WriterReader;

import PokemonGame.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private CSVParameters csvParameters;

    public CSVReader(CSVParameters parameters) {
        this.csvParameters = parameters;
    }


    public String[][] CSVTo2DArray(CSVParameters parameters) {

        List<String> recordsList = new ArrayList<String>();

        String line = "";

        try {
            FileReader file = new FileReader(parameters.filePath);
            BufferedReader bufferedReader = new BufferedReader(file);

            if (parameters.hasHeader) {
                line = bufferedReader.readLine();
                line = null;
            }

            while ((line = bufferedReader.readLine()) != null) {

                recordsList.add(line);
            }
            int recordCount = recordsList.size();

            String[][] array = new String[recordCount][parameters.amountOfFields];
            String[] data;

            for (int i = 0; i < recordCount; i++) {

                data = recordsList.get(i).split(parameters.delimiter);

                for (int j = 0; j < data.length; j++) {
                    array[i][j] = data[j];
                }
            }

            return array;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

