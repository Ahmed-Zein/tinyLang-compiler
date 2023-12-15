package parser;

import java.util.ArrayList;
import java.util.Arrays;

import Utility.Helpers;
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

    public boolean stmtSequence() throws Exception {
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

    public boolean exp() throws Exception {
        // - exp -> simple-exp [ comparison-op simple-exp ]
        simpleExp();
        while (comparisonOP()) {
            simpleExp();
        }
        return false;
    }

    public boolean comparisonOP() throws Exception {
        ArrayList<TokenType> allowedToken = new ArrayList<>(Arrays.asList(
                TokenType.GT,
                TokenType.GTorEQ,
                TokenType.LT,
                TokenType.LTorEQ,
                TokenType.ASSINE));
        for (TokenType tt : allowedToken) {
            if (tt == this.tok.type) {
                match(tt);
                return true;
            }
        }
        return false;
    }

    public boolean simpleExp() throws Exception {
        term();
        while (this.tok.type == TokenType.PLUS || this.tok.type == TokenType.MINUS) {
            match(this.tok.type);
            term();
        }
        return false;
    }

    public boolean term() throws Exception {
        factor();
        while (this.tok.type == TokenType.MULOP) {
            match(TokenType.MULOP);
            factor();
        }
        return false;
    }

    public boolean ifStmt() throws Exception {
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

    public boolean factor() throws Exception {
        // - factor -> exp | number | identifier
        switch (this.tok.type) {
            case OPENBRACKET:
                match(TokenType.OPENBRACKET);
                exp();
                return match(TokenType.CLOSEDBRACKET);
            // break;
            case NUMBER:
                return match(TokenType.NUMBER);
            case IDENTIFIER:
                return match(TokenType.IDENTIFIER);
            default:
                return false;
            // break;
        }
    }

    private boolean match(TokenType type) throws Exception {
        if (this.tok.type == type) {
            System.out.println("INFO: match: " + type.toString());
            getToken();
            return true;
        }
        throw (new Exception("error occured expected: " + type.toString() + Helpers.displayToken(tok)));
    }

    private void getToken() {
        this.tok = this.lexer.nexToken();
    }
}
