package WriterReader;

public class CSVParameters
{
    /*klasse om eender welk CSV file in te lezen en in een 2D array terug te sturen zodat hiervan objecten gemaakt kunnen worden.
    filePath is het path naar de CSV file.
    amountOfFiels is het aantal kolommen.
    delimiter is de scheiding.
    hasHeader is als de CSV een hoofding heeft. indien true schrijft hij de eerste regel niet weg in de 2Darray
     */
    protected String filePath;
    protected int amountOfFields;
    protected String delimiter;
    protected boolean hasHeader;

    public CSVParameters(String filePath, int amountOfFields, String delimiter, boolean hasHeader) {
        this.filePath = filePath;
        this.amountOfFields = amountOfFields;
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
    }
}
