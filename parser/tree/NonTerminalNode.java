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
//        System.out.print(type);
        for (Node tNode : children) {
            if (indent > 0) {
                tree.append("\n│");
//                System.out.print("\n│");
                int tmp = indent;
                while (tmp > 0) {
                    tree.append("    ");
//                    System.out.print("    ");
                    tmp--;
                }
//                System.out.print("├───");
                tree.append("├───");
            } else {
//                System.out.print("\n├───");
                tree.append("\n├───");
            }
//            tNode.drawTree(indent + 1);
            tree.append(tNode.drawTree(indent + 1));
        }
        return tree.toString();
    }
}