package org.example.lexicon;

import org.example.enumerator.TokenType;
import org.example.enumerator.TokenTypeIdentifier;
import org.example.token.IntToken;
import org.example.token.DoubleToken;
import org.example.token.StringToken;
import org.example.token.Token;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;

public class Lexicon {
    private final String[] RESERVED_KEYWORDS = {
        "and", "array", "begin", "case", "const", "div", "do", "downto", "else", "end", "file",
        "for", "function", "goto", "if", "in", "integer", "label", "mod", "nil", "not", "of",
        "or", "packed", "procedure", "program", "read", "read", "real", "record", "repeat", "set",
        "then", "to", "type", "until", "var", "while", "with", "write", "writeln"
    };
    PushbackReader buffer;
    int column;
    int line;

    public Lexicon(String fileName) throws IOException {
        fileName = Paths.get(fileName).toAbsolutePath().toString();
        this.buffer = new PushbackReader(new FileReader(fileName, StandardCharsets.UTF_8));
    }

    public boolean isReservedWord(String word) {
        return Arrays.binarySearch(RESERVED_KEYWORDS, word) >= 0;
    }

    public Token getToken() throws IOException {
        int a;
        char c;
        int e = 0;
        int whiteSpaceAmount = 0;
        StringBuilder sb = new StringBuilder();
        TokenType tp;
        while ((a = this.buffer.read()) != -1) {
            c = (char) a;
            column++;
            if (c == 10 || c == 13) {
                column = 0;
                if (c == 10) {
                    line++;
                }
                continue;
            }
            if (c == ' ') {
                while(c == ' ') {
                    a = this.buffer.read();
                    column++;
                    c = (char) a;
                }
            }
            if (e == 0) {
                if (Character.isLetter(c)) {
                    sb.append(c);
                    e = 1;
                } else if (Character.isDigit(c)) {
                    sb.append(c);
                    e = 2;
                } else {
                    sb.append(c);
                    e = 3;
                }
            }
            if (e == 1) {
                if (Character.isDigit(c) || Character.isLetter(c)) {
                    while (Character.isDigit(c) || Character.isLetter(c)) {
                        a = (char) this.buffer.read();
                        c = (char) a;
                        column++;
                        if (Character.isDigit(c) || Character.isLetter(c)) {
                            sb.append(c);
                        } else {
                            column--;
                            this.buffer.unread(a);
                        }
                    }
                    tp = isReservedWord(sb.toString()) ? TokenType.RESERVED_WORD : TokenType.IDENTIFIER;
                    return new StringToken(sb.toString(), tp, column - sb.toString().length(), line);
                }
            }
            else if (e == 2) {
                if (Character.isDigit(c)) {
                    while ((a = this.buffer.read()) != -1 && (Character.isDigit((char) a))) {
                        column++;
                        sb.append((char) a);
                    }
                    if ((char) a == '.') {
                        sb.append((char) a);
                        a = this.buffer.read();
                        if (!Character.isDigit((char) a)) {
                            throw new RuntimeException("Faltou a parte decimal");
                        }
                        while (Character.isDigit((char) a)) {
                            column++;
                            sb.append((char) a);
                            a = this.buffer.read();
                        }
                        return new DoubleToken(TokenType.REAL, Double.parseDouble(sb.toString()), column, line);
                    } else {
                        return new IntToken(Integer.parseInt(sb.toString()), TokenType.INTEGER, column - sb.toString().length(), line);
                    }
                }
            }
            else {
                if (c == ':') {
                    int next = this.buffer.read();
                    column++;
                    c = (char) next;
                    if (c != '=') {
                        column--;
                        this.buffer.unread(next);
                    }
                    else {
                        sb.append(c);
                    }
                }
                else if(c == '>') {
                    int next = this.buffer.read();
                    column++;
                    c = (char) next;
                    if (c != '=') {
                        column--;
                        this.buffer.unread(next);
                    } else {
                        sb.append(c);
                    }
                }
                else if(c == '<') {
                    int next = this.buffer.read();
                    column++;
                    c = (char) next;
                    if (c != '=' && c != '>') {
                        column--;
                        this.buffer.unread(next);
                    } else {
                        sb.append(c);
                    }
                }
                return new StringToken(sb.toString(), TokenTypeIdentifier.insTabSim(sb.toString()), column - sb.toString().length(), line);
            }
        }
        return null;
    }
}
