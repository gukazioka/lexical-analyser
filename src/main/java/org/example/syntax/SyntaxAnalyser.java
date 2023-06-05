package org.example.syntax;

import org.example.enumerator.TokenType;
import org.example.lexicon.Lexicon;
import org.example.token.Token;

import java.io.IOException;

public class SyntaxAnalyser {
    private Lexicon lexicon;
    Token token;
    public SyntaxAnalyser(String filePath) throws IOException {
        this.lexicon = new Lexicon(filePath);
    }

    public void program() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("program")) {
            token = lexicon.getToken();
            if (token.getTokenType() == TokenType.IDENTIFIER) {
                corpo();
                token = lexicon.getToken();
            } else {
                System.out.println("Faltou o nome do programa");
            }
            if (token == null || token.getTokenType() != TokenType.DOT)
                System.out.println("Faltou o ponto no fim do programa");
        } else
            System.out.println("Não começou com program");
    }

    public void corpo() throws IOException {
        declara();
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("begin")) {
            sentencas();
            if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("end"))) {
                System.out.println("Faltou o end no final do corpo");
            }
        } else {
            System.out.println("Faltou o begin no corpo");
        }

    }

    public void declara() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("var")) {
            dvar();
            mais_dc();
        }
    }

    public void mais_dc() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.SEMI_COLON) {
            cont_dc();
        } else {
            System.out.println("Não possui ponto e vírgula");
        }
    }

    public void cont_dc() throws IOException {
        if (token.getTokenType() == TokenType.IDENTIFIER) {
            dvar();
            mais_dc();
        }
    }

    public void dvar() throws IOException {
        variaveis();
        if (token.getTokenType() == TokenType.COLON) {
            tipo_var();
        } else {
            System.out.println("Não possui ponto e vírgula");
        }
    }

    public void tipo_var() throws IOException {
        token = lexicon.getToken();
        if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("integer"))) {
            System.out.println("Faltou o tipo_var integer");
        }
    }

    public void variaveis() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.IDENTIFIER) {
            mais_var();
        } else {
            System.out.println("Faltou o id da variavel");
        }
    }

    public void mais_var() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.COMMA) {
            variaveis();
        }
    }

    public void sentencas() throws IOException {
        comando();
        mais_sentencas();
    }

    public void mais_sentencas() throws IOException {
        if (token.getTokenType() == TokenType.SEMI_COLON) {
            cont_sentencas();
        } if(token.getTokenType() == TokenType.DOT) {
            return;
        }
    }

    public void cont_sentencas() throws IOException {
        if ((token.getTokenType() != TokenType.RESERVED_WORD))
            sentencas();
    }

    public void var_read() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.IDENTIFIER) {
            mais_var_read();
        } else {
            System.out.println("Faltou o identificador");
        }
    }

    public void mais_var_read() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.COMMA) {
            var_read();
        }
    }

    public void var_write() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.IDENTIFIER) {
            mais_var_write();
        } else {
            System.out.println("Faltou o identificador");
        }
    }

    public void mais_var_write() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.COMMA) {
            var_write();
        }
    }

    public void comando() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.RESERVED_WORD) {
            if (token.compare("read")) {
                token = lexicon.getToken();
                if (token.getTokenType() == TokenType.LEFT_PARENTHESES) {
                    var_read();
                } else {
                    System.out.println("Sem parenteses esquerdo no read");
                }
                if (!(token.getTokenType() == TokenType.RIGHT_PARENTHESES)) {
                    System.out.println("Sem parenteses direito no read");
                }
                token = lexicon.getToken();
            }
            else if (token.compare("write")) {
                token = lexicon.getToken();
                if (token.getTokenType() == TokenType.LEFT_PARENTHESES) {
                    var_write();
                    if (!(token.getTokenType() == TokenType.RIGHT_PARENTHESES)) {
                        System.out.println("Sem parenteses direito no read");
                    } else {
                        token = lexicon.getToken();
                    }
                } else {
                    System.out.println("Sem parenteses esquerdo no read");
                }
            }
            else if (token.compare("for")) {
                token = lexicon.getToken();
                if (token.getTokenType() == TokenType.IDENTIFIER) {
                    token = lexicon.getToken();
                    if (token.getTokenType() == TokenType.ATTRIBUTE) {
                        expressao();
                        if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("to")) {
                            expressao();
                            if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("do")) {
                                token = lexicon.getToken();
                                if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("begin")) {
                                    sentencas();
                                    if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("end"))) {
                                        System.out.println("Faltou o end no loop for");
                                    } else {
                                        token = lexicon.getToken();
                                    }
                                } else {
                                    System.out.println("Faltou o begin no for loop");
                                }
                            } else {
                                System.out.println("Faltou o do no loop for");
                            }
                        } else {
                            System.out.println("Faltou o to");
                        }
                    } else {
                        System.out.println("Faltou o operador de atribuição");
                    }
                } else {
                    System.out.println("Faltou o identificador");
                }
            }
            else if (token.compare("do")) {
                token = lexicon.getToken();
                if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("begin")) {
                    sentencas();
                    token = lexicon.getToken();
                    if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("end"))) {
                        System.out.println("Faltou o end");
                    }
                } else {
                    System.out.println("Faltou o begin");
                }
            }
            else if (token.compare("repeat")) {
                sentencas();
                token = lexicon.getToken();
                if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("until")) {
                    condicao();
                } else {
                    System.out.println("Faltou o until");
                }
            }
            else if (token.compare("while")) {
                condicao();
                token = lexicon.getToken();
                if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("do")) {
                    token = lexicon.getToken();
                    if (token.getTokenType() == TokenType.RESERVED_WORD && token.compare("begin")) {
                        sentencas();
                        token = lexicon.getToken();
                        if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("end"))) {
                            System.out.println("Faltou o end");
                        }
                    } else {
                        System.out.println("Faltou o begin");
                    }
                } else {
                    System.out.println("Faltou o do");
                }
            }
            else if (token.compare("if")) {
                token = lexicon.getToken();
                if (token.getTokenType() != TokenType.LEFT_PARENTHESES) {
                    System.out.println("Faltou o parenteses esquerdo");
                    return;
                }
                condicao();
                token = lexicon.getToken();
                if (token.getTokenType() != TokenType.RIGHT_PARENTHESES) {
                    System.out.println("Faltou o parenteses esquerdo");
                    return;
                }
                token = lexicon.getToken();
                if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("then"))) {
                    System.out.println("Faltou o then");
                    return;
                }
                token = lexicon.getToken();
                if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("begin"))) {
                    System.out.println("Faltou o begin");
                    return;
                }
                sentencas();
                token = lexicon.getToken();
                if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("end"))) {
                    System.out.println("Faltou o end");
                }
                pfalsa();
            }
        }
        else if (token.getTokenType() == TokenType.IDENTIFIER) {
            token = lexicon.getToken();
            if (token.getTokenType() == TokenType.ATTRIBUTE) {
                expressao();
            } else {
                System.out.println("Faltou o operador de atribuição");
            }
        }
    }

    public void pfalsa() throws IOException {
        token = lexicon.getToken();
        if (token.compare("else")) {
            token = lexicon.getToken();
            if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("begin"))) {
                System.out.println("Faltou o begin");
                return;
            }
            sentencas();
            token = lexicon.getToken();
            if (!(token.getTokenType() == TokenType.RESERVED_WORD && token.compare("end"))) {
                System.out.println("Faltou o end");
            }
        }
    }

    public void condicao() throws IOException {
        expressao();
        relacao();
        expressao();
    }

    public void relacao() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() != TokenType.EQUAL &&
                token.getTokenType() != TokenType.GREATER &&
                token.getTokenType() != TokenType.LESS &&
                token.getTokenType() != TokenType.GREATER_EQUAL &&
                token.getTokenType() != TokenType.LESS_EQUAL &&
                token.getTokenType() != TokenType.DIFFERENT) {
            System.out.println("Faltou algum operador de relação");
        }
    }

    public void expressao() throws IOException {
        termo();
        outros_termos();
    }

    public void outros_termos() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.SUBTRACT || token.getTokenType() == TokenType.SUM) {
            termo();
            outros_termos();
        }
    }

    public void termo() throws IOException {
        fator();
        mais_fatores();
    }

    public void mais_fatores() throws IOException {
        if (token.getTokenType() == TokenType.MULTIPLICATION || token.getTokenType() == TokenType.DIVISION) {
            op_mul();
            fator();
            mais_fatores();
        }
    }

    public void op_mul() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() != TokenType.MULTIPLICATION && token.getTokenType() != TokenType.DIVISION) {
            System.out.println("Faltou operador de multiplicação ou divisão");
        }
    }

    public void fator() throws IOException {
        token = lexicon.getToken();
        if (token.getTokenType() == TokenType.IDENTIFIER) {
            return;
        }
        else if (token.getTokenType() == TokenType.INTEGER || token.getTokenType() == TokenType.REAL) {
            return;
        }
        else if (token.getTokenType() == TokenType.LEFT_PARENTHESES) {
            expressao();
            token = lexicon.getToken();
            if (token.getTokenType() != TokenType.RIGHT_PARENTHESES) {
                System.out.println("Faltou o parenteses da direita");
            }
        } else {
            System.out.println("Erro faltou o fator");
        }
    }
}
