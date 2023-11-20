package repl;

import java.util.Scanner;

import lexer.Lexer;
import token.Token;
import token.TokenType;

public class Reple {
    public static void repl() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String line = sc.nextLine();
            Lexer l = new Lexer(line);
            Token t = l.nexToken();
            while (t.type != TokenType.EOF) {
                System.out.println("type: " + t.type + " literal: " + t.literal);
                t = l.nexToken();
            }
        }
    }
}
