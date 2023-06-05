package org.example.token;

import org.example.enumerator.TokenType;

public class IntToken extends Token<Integer> {
    private int value;

    public IntToken(int value, TokenType type, int line, int column) {
        super(type, column, line);
        this.value = value;
        System.out.println(this);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean compare(Integer other) {
        return value == other;
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
