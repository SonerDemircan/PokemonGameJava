package WriterReader;

import java.io.FileReader;

public class CSVParameters
{
    //filename, filepath, splitby...
    //alle parameters wat de reader moet weten wordt hier een object

    private String filePath;
    private int amountOfFields;
    private String delimiter;
    private boolean hasHeader;

    public CSVParameters(String filePath, int amountOfFields, String delimiter, boolean hasHeader) {
        this.filePath = filePath;
        this.amountOfFields = amountOfFields;
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
    }


    public String getDelimiter() { return this.delimiter; }
    public String getFilePath() { return this.filePath; }
    public int getAmountOfFields() { return this.amountOfFields; }
    public boolean isHasHeader() { return this.hasHeader; }

}
