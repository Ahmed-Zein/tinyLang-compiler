import token.Token;
import token.TokenType;

import java.io.File;
import java.util.Scanner;

import lexer.Lexer;
import repl.Reple;

class Main {
    public static void main(String[] args) {
        System.out.println("Terminal(1) or textFile(2)?");
        Scanner input = new Scanner(System.in);
        String hh = input.nextLine();

        if (hh.compareTo("2") == 0) {
            scanFile();
        } else {
            System.out.println("Terminal mode started");
            Reple.repl();
        }
        input.close();
    }

    static void scanFile() {
        System.out.print("Enter file path with extension : ");
        Scanner input = new Scanner(System.in);
        try {
            File file = new File(input.nextLine());
            input.close();
            input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Lexer l = new Lexer(line);
                Token t = l.nexToken();
                while (t.type != TokenType.EOF) {
                    System.out.println(t.literal + ", " + t.type);
                    t = l.nexToken();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            input.close();
        }
    }
}