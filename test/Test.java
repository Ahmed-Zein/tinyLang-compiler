package test;

import parser.*;

public class Test {
    public static void main(String[] args) {
        Parser p = new Parser("()");
        System.out.println(p.factor());;
    }

}
