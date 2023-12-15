package Utility;

import parser.TreeNode;
import token.Token;

public class Helpers {

    public static void drawTree(TreeNode root, int idnt) {
        System.out.print(root.name);
        for (TreeNode tNode : root.children) {
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
            drawTree(tNode, idnt + 1);
        }
    }

    public static String displayToken(Token tok) {
        String displayTxt = "tokenLiteral " + tok.literal + " tokenType: " + tok.type;
        System.out.println(displayTxt);
        return displayTxt;
    }

    static void print(String t) {
        System.out.print(t);
    }

    static void println(String t) {
        System.out.println(t);
    }
}
