package org.example.token;

import org.example.enumerator.TokenType;

public class DoubleToken extends Token{
    private double value;

    public DoubleToken(TokenType type, double value, int line, int column) {
        super(type, line, column);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleToken{" +
                "value=" + value +
                ", type=" + type +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
