package parser;

import java.util.ArrayList;
import java.util.Arrays;

import lexer.Lexer;
import parser.tree.NodeType;
import parser.tree.NonTerminalNode;
import parser.tree.TerminalNode;
import token.Token;
import token.TokenType;

public class Parser {
    private Token tok;
    private Lexer lexer;

    public Parser(String input) {
        lexer = new Lexer(input);
        tok = lexer.nexToken();
    }

    public NonTerminalNode parse() throws Exception {
        return program();
    }

    public NonTerminalNode program() throws Exception {
        // - program -> stmt-sequence
        NonTerminalNode node = new NonTerminalNode(NodeType.program);
        node.addChild(stmtSequence());
        match(TokenType.EOF);
        return node;
    }

    public NonTerminalNode stmtSequence() throws Exception {
        // - stmt-sequence -> statement { ; statement }
        NonTerminalNode node = new NonTerminalNode(NodeType.stmtSequence);
        node.addChild(statement());
        while (this.tok.type == TokenType.SEMICOLON) {
            node.addChild(new TerminalNode(this.tok));
            match(TokenType.SEMICOLON);
            node.addChild(statement());
        }
        return node;
    }

    public NonTerminalNode statement() throws Exception {
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
                NonTerminalNode eNode = new NonTerminalNode(NodeType.eof);
                eNode.addChild(new TerminalNode(this.tok));
                return eNode;
            default:
                throw new Exception(
                        "ERROR: undefined statement" + " ,got: " + this.tok.toString());
        }
    }

    public NonTerminalNode repeatStmt() throws Exception {
        // - repeat-stmt -> **repeat** stmt-sequence **until** exp}
        NonTerminalNode node = new NonTerminalNode(NodeType.repeatStmt);

        TerminalNode repeatNode = new TerminalNode(this.tok);
        match(TokenType.REPEAT);
        node.addChild(repeatNode);

        node.addChild(stmtSequence());

        TerminalNode untilNode = new TerminalNode(tok);
        match(TokenType.UNTIL);
        node.addChild(untilNode);

        node.addChild(exp());
        return node;
    }

    public NonTerminalNode ifStmt() throws Exception {
        // - if-stmt -> if exp then stmt-sequence [ else stmt-sequence ] end
        NonTerminalNode node = new NonTerminalNode(NodeType.ifStmt);
        TerminalNode ifNode = new TerminalNode(this.tok);
        match(TokenType.IF);
        node.addChild(ifNode);
        node.addChild(exp());
        TerminalNode thenNode = new TerminalNode(this.tok);
        match(TokenType.THEN);
        node.addChild(thenNode);
        node.addChild(stmtSequence());

        if (this.tok.type == TokenType.ELSE) {
            node.addChild(new TerminalNode(this.tok));
            match(TokenType.ELSE);
            node.addChild(stmtSequence());
        }
        TerminalNode eNode = new TerminalNode(this.tok);
        match(TokenType.END);
        node.addChild(eNode);
        return node;
    }

    public NonTerminalNode assignStmt() throws Exception {
        // - assign-stmt -> **identifier :=** exp
        NonTerminalNode node = new NonTerminalNode(NodeType.assignStmt);

        TerminalNode idNode = new TerminalNode(this.tok);
        match(TokenType.IDENTIFIER);
        node.addChild(idNode);
        TerminalNode assignNode = new TerminalNode(this.tok);
        match(TokenType.ASSINE);
        node.addChild(assignNode);
        NonTerminalNode expNode = exp();
        node.addChild(expNode);

        return node;
    }

    public NonTerminalNode readStmt() throws Exception {
        // - read-stmt -> **read identifier**
        NonTerminalNode node = new NonTerminalNode(NodeType.readStmt);
        TerminalNode readNode = new TerminalNode(this.tok);
        match(TokenType.READ);
        node.addChild(readNode);
        TerminalNode idNode = new TerminalNode(this.tok);
        match(TokenType.IDENTIFIER);
        node.addChild(idNode);
        return node;
    }

    public NonTerminalNode writeStmt() throws Exception {
        // - write-stmt -> **write** exp
        NonTerminalNode node = new NonTerminalNode(NodeType.writeStmt);
        TerminalNode writeNode = new TerminalNode(this.tok);
        match(TokenType.WRITE);
        node.addChild(writeNode);
        NonTerminalNode expNode = exp();
        node.addChild(expNode);
        return node;
    }

    public NonTerminalNode exp() throws Exception {
        // - exp -> simple-exp [ comparison-op simple-exp ]
        NonTerminalNode node = new NonTerminalNode(NodeType.exp);
        node.addChild(simpleExp());
        TerminalNode t = comparisonOP();
        while (t != null) {
            node.addChild(t);
            node.addChild(simpleExp());
            t = comparisonOP();
        }
        return node;
    }

    public NonTerminalNode simpleExp() throws Exception {
        // - simple-exp -> term { addop term }
        NonTerminalNode node = new NonTerminalNode(NodeType.simpleExp);
        node.addChild(term());
        TerminalNode tNode = new TerminalNode(tok);
        while (this.tok.type == TokenType.PLUS || this.tok.type == TokenType.MINUS) {
            match(this.tok.type);
            node.addChild(tNode);
            node.addChild(term());
            tNode = new TerminalNode(tok);
        }
        return node;
    }

    public NonTerminalNode term() throws Exception {
        // - term -> factor { mulop factor }
        NonTerminalNode node = new NonTerminalNode(NodeType.term);
        node.addChild(factor());
        TerminalNode tNode = new TerminalNode(tok);
        while (this.tok.type == TokenType.MULOP || this.tok.type == TokenType.DIV) {
            match(this.tok.type);
            node.addChild(tNode);
            node.addChild(factor());
            tNode = new TerminalNode(tok);
        }
        return node;
    }

    public NonTerminalNode factor() throws Exception {
        // - factor -> exp | number | identifier
        NonTerminalNode node = new NonTerminalNode(NodeType.factor);
        TerminalNode tNode = new TerminalNode(tok);

        switch (this.tok.type) {
            case OPENBRACKET:
                match(TokenType.OPENBRACKET);
                node.addChild(tNode);
                NonTerminalNode expNode = exp();
                node.addChild(expNode);
                tNode = new TerminalNode(tok);
                match(TokenType.CLOSEDBRACKET);
                node.addChild(tNode);
                return node;
            case NUMBER:
                match(TokenType.NUMBER);
                node.addChild(tNode);
                return node;
            case IDENTIFIER:
                match(TokenType.IDENTIFIER);
                node.addChild(tNode);
                return node;
            default:
                throw new Exception("error occured expected: ( or number or identifier " + " ,got: " + tok.toString());
        }
    }

    public TerminalNode comparisonOP() throws Exception {
        ArrayList<TokenType> allowedToken = new ArrayList<>(Arrays.asList(
                TokenType.GT,
                TokenType.GTorEQ,
                TokenType.LT,
                TokenType.LTorEQ,
                TokenType.ASSINE));
        Token t = this.tok;
        for (TokenType tt : allowedToken) {
            if (tt == this.tok.type) {
                match(tt);
                return new TerminalNode(t);
            }
        }
        return null;
        // throw new Exception("error occured expected: > or >= or < or <= or = " + "
        // ,got: " + tok.toString());
    }

    public TerminalNode mulop() throws Exception {
        Token t = this.tok;
        switch (this.tok.type) {
            case MULOP:
                match(TokenType.MULOP);
                return new TerminalNode(t);
            case DIV:
                match(TokenType.DIV);
                return new TerminalNode(t);
            default:
                throw new Exception("error occured expected: * or / " + " ,got: " + tok.toString());
        }
    }

    public TerminalNode addop() throws Exception {
        Token t = this.tok;
        switch (this.tok.type) {
            case PLUS:
                match(TokenType.PLUS);
                return new TerminalNode(t);
            case MINUS:
                match(TokenType.MINUS);
                return new TerminalNode(t);
            default:
                throw new Exception("error occured expected: + or - " + " ,got: " + tok.toString());
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
