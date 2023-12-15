package test;

import parser.*;

public class Test {
    public static void main(String[] args) {
        Parser p = new Parser("(3)");
        try {
            System.out.println(p.factor());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
