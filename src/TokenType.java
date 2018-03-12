/**
 * @author Tyler Whitfield
 * CS 4308 – Concepts of Programming Languages – Section W01
 * Course Project – 1st Deliverable
 */

import java.util.EnumMap;


public enum TokenType
{
    ID_TOK(100), FUNC_TOK(101), END_TOK(102), IF_TOK(103), THEN_TOK(104),ELSE_TOK(105),WHILE_TOK(106),
    DO_TOK(107), REPEAT_TOK(108), UNTIL_TOK(109),PRINT_TOK(110), LE_TOK(111),LT_TOK(112),GE_TOK(113),
    GT_TOK(114),EQ_TOK(115),NE_TOK(116), ADD_TOK(117), SUB_TOK(118), MUL_TOK(119), DIV_TOK(120), ASSIGN_TOK(121),
    LIT_INT_TOK(122), EOS_TOK(-1), LPAREN_TOK(124), RPAREN_TOK(125), RELATIVE_TOK(126);

    // TokenID Trial run
    private final int TokenID;

    /**
     *
     * @param TokenID
     */
    TokenType(int TokenID)
    {
        this.TokenID=TokenID;
    }

    /**
     *
     * @return
     */
    public int getTokenID()
    {
        return this.TokenID;
    }
}
