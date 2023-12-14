package parser;

import java.util.ArrayList;

public class TreeNode {
    public ArrayList<TreeNode> children;
    public String name;
    public int lineno; // for future support of line numbering
    public NodeType nodeType;

    public TreeNode() {
    }

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

}

enum NodeType {
    stmtSequence,
    comparisonOp,
    repeatStmt,
    assignStmt,
    simpleExp,
    statement,
    writeStmt,
    readStmt,
    program,
    factor,
    ifStmt,
    addop,
    mulop,
    term,
    exp,
}