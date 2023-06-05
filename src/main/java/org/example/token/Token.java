package org.example.token;

import org.example.enumerator.TokenType;

public abstract class Token<T> {
    public TokenType type;
    public int line;
    public int column;

    public Token(TokenType type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }

    public abstract T getValue();
    abstract public void setValue(T value);

    public abstract boolean compare(T other);

    public TokenType getTokenType() {
        return type;
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
