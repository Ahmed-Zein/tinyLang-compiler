package parser;

import java.util.ArrayList;
import java.util.Arrays;

import lexer.Lexer;
import token.Token;
import token.TokenType;

public class Parser {
    private Token tok;
    private Lexer lexer;

    public Parser(String input) {
        lexer = new Lexer(input);
        tok = lexer.nexToken();
    }

    public void parse() {
        tok = lexer.nexToken();
    }

    public boolean statement() {
        return false;
    }

    public boolean stmtSequence() {
        switch (this.tok.type) {
            case IF:
                ifStmt();
                break;
            case REPEAT:
                break;
            case ASSINE:
                break;
            case READ:
                break;
            case WRITE:
                break;
            default:
                return false;
        }
        return false;
    }

    public boolean exp() {
        // - exp -> simple-exp [ comparison-op simple-exp ]
        simpleExp();
        while (comparisonOP()) {
            simpleExp();
        }
        return false;
    }

    public boolean comparisonOP() {
        ArrayList<TokenType> allowedToken = new ArrayList<>(Arrays.asList(
                TokenType.GT,
                TokenType.GTorEQ,
                TokenType.LT,
                TokenType.LTorEQ,
                TokenType.ASSINE));
        for (TokenType tt : allowedToken) {
            if (match(tt)) {
                return true;
            }
        }
        return false;
    }

    public boolean simpleExp() {
        term();
        while (this.tok.type == TokenType.PLUS || this.tok.type == TokenType.MINUS) {
            match(this.tok.type);
            term();
        }
        return false;
    }

    public boolean term() {
        factor();
        while (this.tok.type == TokenType.MULOP) {
            match(TokenType.MULOP);
            factor();
        }
        return false;
    }

    public boolean ifStmt() {
        // - if-stmt -> if exp then stmt-sequence [ else stmt-sequence ] end
        match(TokenType.IF);
        exp();
        match(TokenType.THEN);
        statement();
        if (this.tok.type == TokenType.ELSE) {
            match(TokenType.ELSE);
        }
        match(TokenType.END);
        return false;
    }

    public boolean factor() {
        // - factor -> exp | number | identifier
        switch (this.tok.type) {
            case OPENBRACKET:
                match(TokenType.OPENBRACKET);
                exp();
                match(TokenType.CLOSEDBRACKET);
                return match(TokenType.CLOSEDBRACKET);
            // break;
            case NUMBER:
                return match(TokenType.NUMBER);
            case IDENTIFIER:
                return match(TokenType.NUMBER);
            default:
                return false;
            // break;
        }

    }

    private boolean match(TokenType type) {
        if (this.tok.type == type) {
            getToken();
            return true;
        }
        return false;
    }

    private void getToken() {
        this.tok = this.lexer.nexToken();
    }
}
