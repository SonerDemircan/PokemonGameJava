package WriterReader;

import java.io.FileReader;

public class CSVParameters
{
    //filename, filepath, splitby...
    //alle parameters wat de reader moet weten wordt hier een object

    private String filePath;
    private int amountOfFields;
    private String delimiter;
    private boolean readHeader;

    public CSVParameters(String filePath, int amountOfFields, String delimiter) {
        this.filePath = filePath;
        this.amountOfFields = amountOfFields;
        this.delimiter = delimiter;
    }


    public boolean is_ReadHeader() {
        return readHeader;
    }

    public void set_ReadHeader(boolean readHeader) {
        this.readHeader = readHeader;
    }


    public String getDelimiter() { return this.delimiter; }
    public String getFilePath() { return  this.filePath; }
    public int getAmountOfFields() { return this.amountOfFields; }

}
