/**
 * @author Tyler Whitfield
 * CS 4308 – Concepts of Programming Languages – Section W01
 * Course Project – 1st Deliverable
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Character;

public class LexicalAnalyzer
{
    private List<Token> tokens;

    /**
     * Takes fileName and checks that it isn't null, then
     * @param fileName the string of the filepath for the Lua program to be scanned
     * @throws FileNotFoundException
     * @throws LexicalException
     */
    public LexicalAnalyzer(String fileName)
        throws FileNotFoundException, LexicalException
    {
        assert (fileName != null);
        tokens = new ArrayList<Token>();
        Scanner sourceCode = new Scanner (new File (fileName));
        int lineNumber = 0;
        int end = 0;
        while (sourceCode.hasNext())
        {
            String line = sourceCode.nextLine();
            processLine (line,lineNumber);
            end = line.length();
            lineNumber++;
        }
        tokens.add(new Token(lineNumber,end +1, "EOS",TokenType.EOS_TOK));
        sourceCode.close();
    }

    /**
     *
     * @param line
     * @param lineNumber
     * @throws LexicalException
     */
    private void processLine(String line, int lineNumber)
        throws LexicalException
    {
        assert (line != null && lineNumber >= 1);
        int index = 0;
        index = skipWhiteSpace (line, index);
        while (index < line.length())
        {
            String lexeme = getLexeme (line,lineNumber,index);
            TokenType tokType = getTokenType (lexeme,lineNumber,index);
            tokens.add(new Token (lineNumber + 1, index +1, lexeme, tokType));
            index += lexeme.length();
            index = skipWhiteSpace (line, index);
        }
    }

    /**
     *
     * @param lexeme
     * @param lineNumber
     * @param columnNumber
     * @return
     * @throws LexicalException
     */
    private TokenType getTokenType(String lexeme, int lineNumber, int columnNumber)
            throws LexicalException
    {
        assert (lexeme != null && lineNumber >=1 && columnNumber >=1);
        TokenType tokType = null;
        if (Character.isLetter(lexeme.charAt(0)))
        {
            if (lexeme.length() == 1)
                tokType = TokenType.ID_TOK;
            else if (lexeme.equals("function"))
                tokType = TokenType.FUNC_TOK;
            else if (lexeme.equals("if"))
                tokType = TokenType.IF_TOK;
            else if (lexeme.equals("end"))
                tokType = TokenType.END_TOK;
            else if (lexeme.equals("relative"))
                tokType = TokenType.RELATIVE_TOK;
            else if (lexeme.equals("then"))
                tokType = TokenType.THEN_TOK;
            else if (lexeme.equals("else"))
                tokType = TokenType.ELSE_TOK;
            else if (lexeme.equals("while"))
                tokType = TokenType.WHILE_TOK;
            else if (lexeme.equals("do"))
                tokType = TokenType.DO_TOK;
            else if (lexeme.equals("print"))
                tokType = TokenType.PRINT_TOK;
            else if (lexeme.equals("repeat"))
                tokType = TokenType.REPEAT_TOK;
            else if (lexeme.equals("until"))
                tokType = TokenType.UNTIL_TOK;
            else
                throw new LexicalException ("Invalid lexeme at row number " +
                        (lineNumber +1) + " and column " + (columnNumber +1));
        }
        else if (Character.isDigit(lexeme.charAt(0)))
        {
            if (allDigits(lexeme))
                tokType = TokenType.LIT_INT_TOK;
            else
                throw new LexicalException ("Invalid lexeme at row number " +
                        (lineNumber + 1) + " and column " + (columnNumber + 1));
        }
        else if (lexeme.equals("+"))
            tokType = TokenType.ADD_TOK;
        else if (lexeme.equals("*"))
            tokType = TokenType.MUL_TOK;
        else if (lexeme.equals("-"))
            tokType = TokenType.SUB_TOK;
        else if (lexeme.equals("/"))
            tokType = TokenType.DIV_TOK;
        else if (lexeme.equals("="))
            tokType = TokenType.ASSIGN_TOK;
        else if (lexeme.equals("<="))
            tokType = TokenType.LE_TOK;
        else if (lexeme.equals("<"))
            tokType = TokenType.LT_TOK;
        else if (lexeme.equals(">="))
            tokType = TokenType.GE_TOK;
        else if (lexeme.equals(">"))
            tokType = TokenType.GT_TOK;
        else if (lexeme.equals("=="))
            tokType = TokenType.EQ_TOK;
        else if (lexeme.equals("~="))
            tokType = TokenType.NE_TOK;
        else if (lexeme.equals("("))
            tokType = TokenType.LPAREN_TOK;
        else if (lexeme.equals(")"))
            tokType = TokenType.RPAREN_TOK;
        else {
            throw new LexicalException ("Invalid lexeme at row number " +
                    (lineNumber + 1) + " and column " + (columnNumber + 1));
        }
        return tokType;
    }

    /**
     *
     * @param s
     * @return
     */
    private boolean allDigits(String s)
    {
        assert (s != null);
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i)))
            i++;
        return i == s.length();
    }

    /**
     * Asserts preconditions that the string is not null, the lexeme is at row one
     * or greater, and the first character is at zero or greater.
     * When assertion is met, value i is initialized to index and iterated
     * over the length of lexeme string so long as the next char is not a white space.
     * Returns the string value slice from index to i.
     * @param line the string currently being analyzed
     * @param lineNumber the int reference for the current row being analyzed
     * @param index the int value of the start of the lexeme
     * @return the string value of line from index to i
     */
    private String getLexeme(String line, int lineNumber, int index)
    {
        assert (line != null && lineNumber >= 1 && index >= 0);
        int i = index;
        while (i < line.length() && !Character.isWhitespace(line.charAt(i)))
            i++;
        return line.substring(index, i);
    }

    /**
     *
     * @param line the string being analyzed
     * @param index the int used to iterate over the line
     * @return
     */
    private int skipWhiteSpace(String line, int index)
    {
        assert (line !=null && index >=0);
        while (index < line.length() && Character.isWhitespace(line.charAt(index)))
            index++;
        return index;
    }

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws LexicalException
     */
    public static void main(String[] args)
            throws FileNotFoundException,LexicalException
    {
        Scanner sourceCode = new Scanner(System.in);
        System.out.print("Please enter the path to the file to be tokenized: ");
        String fileName = sourceCode.nextLine();
        LexicalAnalyzer scanner = new LexicalAnalyzer(fileName);
        Token.printTableHeader();
        for (int i=0; i <scanner.tokens.size(); i++)
             System.out.println(scanner.tokens.get(i).toString());
        Token.printTableBorder();
        System.out.println(scanner.tokens.size() + " tokens processed");
    }

}