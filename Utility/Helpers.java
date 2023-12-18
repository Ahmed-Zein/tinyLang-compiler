// package Utility;

// import parser.tree.TreeNode;

// public class Helpers {

//     public static void drawTree(TreeNode root, int idnt) {
//         System.out.print(root.name);
//         for (TreeNode tNode : root.children) {
//             if (idnt > 0) {
//                 System.out.print("\n│");
//                 int tmp = idnt;
//                 while (tmp > 0) {
//                     System.out.print("    ");
//                     tmp--;
//                 }
//                 System.out.print("├───");
//             } else {
//                 System.out.print("\n├───");
//             }
//             drawTree(tNode, idnt + 1);
//         }
//     }
// }
