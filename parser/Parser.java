package parser;

import java.util.ArrayList;
import java.util.Arrays;

import exception.UnExceptedToken;
import exception.UndefinedStmt;
import lexer.Lexer;
import parser.tree.NodeType;
import parser.tree.NonTerminalNode;
import parser.tree.TerminalNode;
import token.Token;
import token.TokenType;
// i will change the match to return the nonterminal node and handle the exception internally

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
            node.addChild(match(TokenType.SEMICOLON));
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
                // return new NonTerminalNode(NodeType.undefinedstmt);
                throw new UndefinedStmt(
                        "ERROR: undefined statement" + ", got: " + this.tok.toString());
        }
    }

    public NonTerminalNode repeatStmt() throws Exception {
        // - repeat-stmt -> **repeat** stmt-sequence **until** exp}
        NonTerminalNode node = new NonTerminalNode(NodeType.repeatStmt);

        node.addChild(match(TokenType.REPEAT));

        node.addChild(stmtSequence());

        node.addChild(match(TokenType.UNTIL));

        node.addChild(exp());

        return node;
    }

    public NonTerminalNode ifStmt() throws Exception {
        // - if-stmt -> if exp then stmt-sequence [ else stmt-sequence ] end
        NonTerminalNode node = new NonTerminalNode(NodeType.ifStmt);
        node.addChild(match(TokenType.IF));
        node.addChild(exp());
        node.addChild(match(TokenType.THEN));
        node.addChild(stmtSequence());

        if (this.tok.type == TokenType.ELSE) {
            node.addChild(match(TokenType.ELSE));
            node.addChild(stmtSequence());
        }
        node.addChild(match(TokenType.END));
        return node;
    }

    public NonTerminalNode assignStmt() throws Exception {
        // - assign-stmt -> **identifier :=** exp
        NonTerminalNode node = new NonTerminalNode(NodeType.assignStmt);

        node.addChild(match(TokenType.IDENTIFIER));

        node.addChild(match(TokenType.ASSINE));

        node.addChild(exp());

        return node;
    }

    public NonTerminalNode readStmt() throws Exception {
        // - read-stmt -> **read identifier**
        NonTerminalNode node = new NonTerminalNode(NodeType.readStmt);
        node.addChild(match(TokenType.READ));
        node.addChild(match(TokenType.IDENTIFIER));
        return node;
    }

    public NonTerminalNode writeStmt() throws Exception {
        // - write-stmt -> **write** exp
        NonTerminalNode node = new NonTerminalNode(NodeType.writeStmt);
        node.addChild(match(TokenType.WRITE));
        node.addChild(exp());
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
        while (this.tok.type == TokenType.PLUS || this.tok.type == TokenType.MINUS) {
            node.addChild(match(this.tok.type));
            node.addChild(term());
        }
        return node;
    }

    public NonTerminalNode term() throws Exception {
        // - term -> factor { mulop factor }
        NonTerminalNode node = new NonTerminalNode(NodeType.term);
        node.addChild(factor());
        while (this.tok.type == TokenType.MULOP || this.tok.type == TokenType.DIV) {
            node.addChild(match(this.tok.type));
            node.addChild(factor());
        }
        return node;
    }

    public NonTerminalNode factor() throws Exception {
        // - factor -> exp | number | identifier
        NonTerminalNode node = new NonTerminalNode(NodeType.factor);

        switch (this.tok.type) {
            case OPENBRACKET:
                node.addChild(match(TokenType.OPENBRACKET));
                node.addChild(exp());
                node.addChild(match(TokenType.CLOSEDBRACKET));
                return node;
            case NUMBER:
                node.addChild(match(TokenType.NUMBER));
                return node;
            case IDENTIFIER:
                node.addChild(match(TokenType.IDENTIFIER));
                return node;
            default:
                TerminalNode err = new TerminalNode(new Token(TokenType.UNEXCEPTED_TOKEN, "expectd: * or /"));
                node.addChild(err);
                return node;
            // throw new UnExceptedToken(
            // "error occured expected: ( or number or identifier" + ", got: " +
            // tok.toString());
        }
    }

    public TerminalNode comparisonOP() throws Exception {
        ArrayList<TokenType> allowedToken = new ArrayList<>(Arrays.asList(
                TokenType.GT,
                TokenType.GTorEQ,
                TokenType.LT,
                TokenType.LTorEQ,
                TokenType.ASSINE));
        for (TokenType tt : allowedToken) {
            if (tt == this.tok.type) {
                return match(tt);
            }
        }
        return null;
    }

    public TerminalNode mulop() throws Exception {
        switch (this.tok.type) {
            case MULOP:
                return match(TokenType.MULOP);
            case DIV:
                return match(TokenType.DIV);
            default:
                return new TerminalNode(new Token(TokenType.UNEXCEPTED_TOKEN, "expectd: * or /"));
            // throw new Exception("error occured expected: * or / " + " ,got: " +
            // tok.toString());
        }
    }

    public TerminalNode addop() throws Exception {
        switch (this.tok.type) {
            case PLUS:
                return match(TokenType.PLUS);
            case MINUS:
                return match(TokenType.MINUS);
            default:
                return new TerminalNode(new Token(TokenType.UNEXCEPTED_TOKEN, "expectd: + or -"));
            // throw new Exception("error occured expected: + or - " + " ,got: " +
            // tok.toString());
        }
    }

    private TerminalNode match(TokenType type) throws Exception {
        if (this.tok.type == type) {
            System.out.println("INFO: match: " + tok.toString());
            TerminalNode tn = new TerminalNode(this.tok);
            getToken();
            return tn;
        }
        System.out.println("ERROR: match expected: " + type.toString() + " ,got: " + tok.toString());
        return new TerminalNode(new Token(TokenType.UNEXCEPTED_TOKEN, "expectd: " + type.toString()));
        // throw (new UnExceptedToken(this.tok, type));
    }

    private void getToken() {
        this.tok = this.lexer.nexToken();
    }
}
