package WriterReader;

import java.io.FileReader;

public class CSVParameters
{
    /*klasse om eender welk CSV file in te lezen en in een 2D array terug te sturen zodat hiervan objecten gemaakt kunnen worden.
    filePath is het path naar de CSV file.
    amountOfFiels is het aantal kolommen.
    delimiter is de scheiding.
    hasHeader is als de CSV een hoofding heeft. indien true schrijft hij de eerste regel niet weg in de 2Darray
     */

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
