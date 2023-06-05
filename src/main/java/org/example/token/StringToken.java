package org.example.token;

import org.example.enumerator.TokenType;

public class StringToken extends Token<String>{
    private String value;

    public StringToken(String value, TokenType type, int column, int line) {
        super(type, line, column);
        this.value = value;
        System.out.println(this);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean compare(String other) {
        return value.equalsIgnoreCase(other);
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
