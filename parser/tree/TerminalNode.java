package parser.tree;

import token.Token;

public class TerminalNode extends Node {
    Token tok;

    public TerminalNode(Token tok) {
        this.tok = tok;
    }

    public String toString() {
        return tok.toString();
    }

    @Override
    public void drawTree(int depth) {
        System.out.print(this.tok.toString());
    }
}
