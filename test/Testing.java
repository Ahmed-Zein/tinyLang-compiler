package test;

import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import parser.*;
import parser.tree.NonTerminalNode;

public class Testing {
    @Test
    public void assignTree() {
        String input = "a := 1;";
        Parser parser = new Parser(input);
        try {
            NonTerminalNode assignStmt = parser.assignStmt();
            assignStmt.drawTree(0);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void parseTree() {
        String inpString = "if 5>9 then write 5 else write 9; read x; write 88; repeat write 88+1 until  10 end;  ";
        Parser parser = new Parser(inpString);
        try {
            parser.parse().drawTree(0);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void parseTree2() {
        String inpString = " x; x:=24;";
        Parser parser = new Parser(inpString);
        try {
            parser.parse().drawTree(0);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
}
