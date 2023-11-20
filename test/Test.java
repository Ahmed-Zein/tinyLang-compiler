package test;

import java.util.Scanner;
import java.io.File;

import lexer.Lexer;
import token.Token;
import token.TokenType;

public class Test {
    public static void testInput1() {
        //String input = "\n {comment} x:= 55; if(x <= 55) then x=34 else x= 44 end unitl#read write /*-+09090";
    	System.out.println("Terminal(1) or textFile(2)?");
        Scanner input = new Scanner(System.in);
        String hh = input.nextLine();
        
        if (hh.compareTo("2")==0) {
        	repl2();
        }
        else {
        	System.out.println("Terminal Opened");
        	repl1();
        }
        input.close();
    }
    static void repl1() {
        while (true) {
            System.out.print(">>");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            Lexer l = new Lexer(line);
            Token t = l.nexToken();
            while (t.type != TokenType.EOF) {
            	System.out.println(t.literal + ", " + t.type);
                t = l.nexToken();
            }
        }
    }
    static void repl2() {
    	try {
            System.out.print("Enter file path with extension : ");
            Scanner input = new Scanner(System.in);
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
        } 
    	catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
