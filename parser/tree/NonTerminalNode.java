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
    public void drawTree(int idnt) {
        System.out.print(type);
        for (Node tNode : children) {
            if (idnt > 0) {
                System.out.print("\n│");
                int tmp = idnt;
                while (tmp > 0) {
                    System.out.print("    ");
                    tmp--;
                }
                System.out.print("├───");
            } else {
                System.out.print("\n├───");
            }
            tNode.drawTree(idnt + 1);
        }
    }
}