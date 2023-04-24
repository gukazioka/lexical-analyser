package org.example.token;

import org.example.enumerator.TokenType;

public class StringToken extends Token{
    private String value;

    public StringToken(String value, TokenType type, int column, int line) {
        super(type, line, column);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "value='" + value + '\'' +
                ", type=" + type +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
