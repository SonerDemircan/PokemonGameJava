package WriterReader;

import Interfaces.ICSVWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter implements ICSVWriter {

    BufferedWriter writer;

    @Override
    public void writeFile(List<String> input) {

        int size = input.size();

        try {

            writer = new BufferedWriter(new FileWriter("src/CSV/output.csv"));

            for (int i = 0; i < size; i++) {
                writer.write(input.get(i));

            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
