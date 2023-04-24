package org.example.enumerator;

public class TokenTypeIdentifier {
    static public TokenType insTabSim(String lexema) {
        if (lexema.equals("(")) {
            return TokenType.LEFT_PARENTHESES;
        } else if (lexema.equals(")")) {
            return TokenType.RIGHT_PARENTHESES;
        } else if (lexema.equals("+")) {
            return TokenType.SUM;
        } else if (lexema.equals("-")) {
            return TokenType.SUBTRACT;
        } else if (lexema.equals("*")) {
            return TokenType.MULTIPLICATION;
        } else if (lexema.equals("/")) {
            return TokenType.DIVISION;
        } else if (lexema.equals(".")) {
            return TokenType.DOT;
        } else if (lexema.equals(";")) {
            return TokenType.SEMI_COLON;
        } else if (lexema.equals(">")) {
            return TokenType.GREATER;
        } else if (lexema.equals("<")) {
            return TokenType.LESS;
        } else if (lexema.equals(">=")) {
            return TokenType.GREATER_EQUAL;
        } else if (lexema.equals("<=")) {
            return TokenType.LESS_EQUAL;
        } else if (lexema.equals("<>")) {
            return TokenType.DIFFERENT;
        } else if (lexema.equals("=")) {
            return TokenType.EQUAL;
        } else if (lexema.equals("{")) {
            return TokenType.LEFT_BRACKET;
        } else if (lexema.equals("}")) {
            return TokenType.RIGHT_BRACKET;
        } else if (lexema.equals((":"))) {
            return TokenType.COLON;
        } else if (lexema.equals((":="))) {
            return TokenType.ATTRIBUTE;
        } else {
            return null;
        }
    }
}
