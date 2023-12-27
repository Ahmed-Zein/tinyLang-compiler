package parser.tree;

import java.util.ArrayList;

public class NonTerminalNode extends Node {
    public NodeType type;
    public ArrayList<Node> children;

    public NonTerminalNode(NodeType type) {
        this.type = type;
        this.children = new ArrayList<Node>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public String drawTree(int indent) {
        StringBuilder tree = new StringBuilder(type.toString());
        for (Node tNode : children) {
            if (indent > 0) {
                tree.append("\n│");
                int tmp = indent;
                while (tmp > 0) {
                    tree.append("    ");
                    tmp--;
                }
                tree.append("├───");
            } else {
                tree.append("\n├───");
            }
            tree.append(tNode.drawTree(indent + 1));
        }
        return tree.toString();
    }
}