package test;

import lexer.Lexer;
import token.Token;
import token.TokenType;

public class Test {
    public static void testInput1() {
        String input = "\n {adskfjh;alskdjflk} x:= 55; if(x >= 55) then x=34 else x= 44 end unitl#read write /*-+09090";
        Lexer l = new Lexer(input);
        Token t = l.nexToken();
        while (t.type != TokenType.EOF) {
                System.out.println("type: " + t.type + " literal: " + t.literal);
                t = l.nexToken();
        }
    }

}