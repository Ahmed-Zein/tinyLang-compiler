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

    public void parse()throws Exception {
        program();
    }

    public boolean program() throws Exception {
        // - program -> stmt-sequence
        stmtSequence();
        return match(TokenType.EOF);
    }

    public boolean stmtSequence() throws Exception {
        // - stmt-sequence -> statement { ; statement }
        statement();
        while (this.tok.type == TokenType.SEMICOLON) {
            match(TokenType.SEMICOLON);
            statement();
        }
        return true;
    }

    public boolean statement() throws Exception {
        // - statement -> stmt-sequence | if-stmt | repeat-stmt | assign-stmt |
        // read-stmt | write-stmt
        switch (this.tok.type) {
            case IF:
                return ifStmt();
            case REPEAT:
                return repeatStmt();
            case IDENTIFIER:
                return assignStmt();
            case READ:
                return readStmt();
            case WRITE:
                return writeStmt();
            case EOF:
                return true;
            default:
                throw new Exception(
                        "ERROR: statement expected: " + " ,got: " + this.tok.toString());
        }
    }

    public boolean repeatStmt() throws Exception {
        // - repeat-stmt -> **repeat** stmt-sequence **until** exp}
        match(TokenType.REPEAT);
        stmtSequence();
        match(TokenType.UNTIL);
        return exp();
    }

    public boolean ifStmt() throws Exception {
        // - if-stmt -> if exp then stmt-sequence [ else stmt-sequence ] end
        match(TokenType.IF);
        exp();
        match(TokenType.THEN);
        stmtSequence();
        if (this.tok.type == TokenType.ELSE) {
            match(TokenType.ELSE);
            stmtSequence();
        }

        match(TokenType.END);
        return false;
    }

    public boolean assignStmt() throws Exception {
        // - assign-stmt -> **identifier :=** exp
        match(TokenType.IDENTIFIER);
        match(TokenType.ASSINE);
        return exp();
    }

    public boolean readStmt() throws Exception {
        // - read-stmt -> **read identifier**
        match(TokenType.READ);
        return match(TokenType.IDENTIFIER);
    }

    public boolean writeStmt() throws Exception {
        // - write-stmt -> **write** exp
        match(TokenType.WRITE);
        return exp();
    }

    public boolean exp() throws Exception {
        // - exp -> simple-exp [ comparison-op simple-exp ]
        simpleExp();
        while (comparisonOP()) {
            simpleExp();
        }
        return false;
    }

    public boolean simpleExp() throws Exception {
        // - simple-exp -> term { addop term }
        term();
        while (this.tok.type == TokenType.PLUS || this.tok.type == TokenType.MINUS) {
            match(this.tok.type);
            term();
        }
        return false;
    }

    public boolean term() throws Exception {
        // - term -> factor { mulop factor }
        factor();
        while (this.tok.type == TokenType.MULOP || this.tok.type == TokenType.DIV) {
            match(this.tok.type);
            factor();
        }
        return false;
    }

    public boolean factor() throws Exception {
        // - factor -> exp | number | identifier
        switch (this.tok.type) {
            case OPENBRACKET:
                match(TokenType.OPENBRACKET);
                exp();
                return match(TokenType.CLOSEDBRACKET);
            case NUMBER:
                return match(TokenType.NUMBER);
            case IDENTIFIER:
                return match(TokenType.IDENTIFIER);
            default:
                return false;
        }
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

    public boolean mulop() throws Exception {
        switch (this.tok.type) {
            case MULOP:
                return match(TokenType.MULOP);
            case DIV:
                return match(TokenType.DIV);

            default:
                return false;
        }
    }

    public boolean addop() throws Exception {
        switch (this.tok.type) {
            case PLUS:
                return match(TokenType.PLUS);
            case MINUS:
                return match(TokenType.MINUS);

            default:
                return false;
        }
    }

    private boolean match(TokenType type) throws Exception {
        if (this.tok.type == type) {
            // +type.toString()
            System.out.println("INFO: match: " + tok.toString());
            getToken();
            return true;
        }
        throw (new Exception("error occured expected: " + type.toString() + " ,got: " + tok.toString()));
    }

    private void getToken() {
        this.tok = this.lexer.nexToken();
    }
}
