package org.example.token;

import org.example.enumerator.TokenType;

public class DoubleToken extends Token<Double> {
    private double value;

    public DoubleToken(TokenType type, double value, int line, int column) {
        super(type, line, column);
        this.value = value;
        System.out.println(this);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean compare(Double other) {
        return value == other;
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
