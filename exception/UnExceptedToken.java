package exception;

import token.Token;
import token.TokenType;

public class UnExceptedToken extends Exception {
    Token tok;

    public UnExceptedToken(Token tok, TokenType type) {
        // "error occured expected: " + type.toString() + " ,got: " + tok.toString()
        super("UnExceptedToken: excpected{" + type + "} got{" + tok.toString() + "}");
        this.tok = tok;
    }

    public UnExceptedToken(String s) {
        super(s);
    }
}
