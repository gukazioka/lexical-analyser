package org.example.token;

import org.example.enumerator.TokenType;

public class IntToken extends Token {
    private int value;

    public IntToken(int value, TokenType type, int line, int column) {
        super(type, column, line);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntToken{" +
                "value=" + value +
                ", type=" + type +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
