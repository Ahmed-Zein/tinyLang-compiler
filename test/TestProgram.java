package test;

import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import parser.Parser;

public class TestProgram {
    @Test
    public void test() {
        String inpString = "if 5>9 then write 5 else write 9; read x; write 88; repeat write 88+1 until  10 end;  ";
        Parser parser = new Parser(inpString);
        try {
            parser.parse();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void test2() {
        String inString = "read x;\nif 0<x then \nfact:=1;\nrepeat fact:=fact*x;x:=x-1    \nuntil x = 0;    \nwrite fact\nend";
        Parser parser = new Parser(inString);
        try {
            parser.parse();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
}
