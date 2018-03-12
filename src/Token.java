/**
 * @author Tyler Whitfield
 * CS 4308 – Concepts of Programming Languages – Section W01
 * Course Project – 1st Deliverable
 */

import java.util.Arrays;

public class Token {
    private int rowNumber;
    private int columnNumber;
    private String lexeme;
    private TokenType tokType;

    /**
     *
     * @param rowNumber the int specifying which vertical row line is being read
     * @param columnNumber  the int specifying which horizontal column line is being read
     * @param lexeme the String
     * @param tokType
     */
    public Token(int rowNumber,int columnNumber,String lexeme,TokenType tokType)
    {
        if (rowNumber <=0)
            throw new IllegalArgumentException("Invalid row number argument");
        if (columnNumber <=0)
            throw new IllegalArgumentException("Invalid column number argument");
        if (lexeme == null || lexeme.length()==0)
            throw new IllegalArgumentException("Invalid lexeme argument");
        if (tokType == null)
            throw new IllegalArgumentException("Invalid TokenType argument");
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.lexeme = lexeme;
        this.tokType = tokType;
        }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return String.format("%-10s | %-12s | %5d | %3d | %6d", lexeme, tokType, tokType.getTokenID(), rowNumber, columnNumber);
       // return "Lexeme " + lexeme + " returns token of " + tokType + " and ID " + tokType.getTokenID() + " at column " + columnNumber + " and row " + rowNumber;
    }

    /**
     *
     * @return
     */
    private static String getTableHeader()
    {
        StringBuilder header = new StringBuilder(String.format(
                "%-10s | %-12s | %-5s | %-3s | %-6s", "Lexeme", "Token", "ID", "Row", "Column"));
        char[] line = new char[header.length()];
        Arrays.fill(line, '-');
        return header.append("\n").append(new String(line)).toString();
    }

    /**
     *
     */
    public static void printTableHeader()
    {
        System.out.println(getTableHeader());
    }

    /**
     *
     */
    public static void printTableBorder()
    {
        StringBuilder border = new StringBuilder(String.format(""));
        char[] borderLine = new char[getTableHeader().length()/2];
        Arrays.fill(borderLine,'-');
        System.out.println(border.append(new String(borderLine)).toString());
    }

    /**
     *
     * @return
     */
    public int getRowNumber()
    {
        return rowNumber;
    }

    /**
     *
     * @return
     */
    public int getColumnNumber()
    {
        return columnNumber;
    }

    /**
     *
     * @return
     */
    public String getLexeme()
    {
        return lexeme;
    }

    /**
     *
     * @return
     */
    public TokenType getTokType()
    {
        return tokType;
    }
}
