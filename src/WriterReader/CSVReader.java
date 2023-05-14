package WriterReader;

import PokemonGame.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private List<String> x = new ArrayList<>();
    private CSVParameters csvParameters;

    public CSVReader(CSVParameters parameters) {
        this.csvParameters = parameters;
    }


    public String[][] CSVTo2DArray(CSVParameters parameters) {

        List<String> recordsList = new ArrayList<String>();

        String line = "";

        try {
            FileReader file = new FileReader(parameters.getFilePath());
            BufferedReader bufferedReader = new BufferedReader(file);

            while ((line = bufferedReader.readLine()) != null) {

                recordsList.add(line);
            }
            int recordCount = recordsList.size();

            String[][] array = new String[recordCount][parameters.getAmountOfFields()];
            String[] data;

            for (int i = 0; i < recordCount; i++) {

                data = recordsList.get(i).split(parameters.getDelimiter());

                for (int j = 0; j < data.length; j++) {
                    array[i][j] = data[j];
                }
            }

            return array;

                /*
                if (i > 0) {
                    String[] lineContent = line.split(splitBy);

                    content = new String[lineContent.length][3];

                    //pokemon = new Pokemon(Integer.parseInt(lineContent[0]), lineContent[1], lineContent[2],Integer.parseInt(lineContent[3]),Integer.parseInt(lineContent[4]),Integer.parseInt(lineContent[5]),Integer.parseInt(lineContent[6]),Integer.parseInt(lineContent[7]));
                    //pokemonList.add(pokemon);

                    for(int j = 0; j < lineContent.length; j++) {
                        content[i][j] = lineContent[j];
                    }
                }
                i++;

                 */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

