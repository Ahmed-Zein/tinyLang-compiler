package parser.tree;

import token.Token;

public class TerminalNode extends Node {
   public Token tok;

    public TerminalNode() {
    }

    public TerminalNode(Token tok) {
        this.tok = tok;
    }

    public String toString() {
        return tok.toString();
    }

    @Override
    public String drawTree(int depth) {
        return this.tok.toString();
//        System.out.print(this.tok.toString());
    }
}
