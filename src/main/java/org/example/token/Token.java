package org.example.token;

import org.example.enumerator.TokenType;

abstract public class Token {
    public TokenType type;
    public int line;
    public int column;

    public Token(TokenType type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
