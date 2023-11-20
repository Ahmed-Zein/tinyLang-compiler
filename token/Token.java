package token;

public class Token {
    public TokenType type;
    public String literal;

    public Token() {
    }

    public Token(TokenType type, String literal) {
        this.literal = literal;
        this.type = type;
    }
    public Token(TokenType type, char literal) {
        this.literal = String.valueOf(literal);
        this.type = type;
    }

    public static TokenType lookup(String literal) {
        TokenType tt;
        try {
            tt = TokenType.valueOf(literal.toUpperCase());
        } catch (IllegalArgumentException e) {
            tt = TokenType.IDENTIFIER;
        }
        return tt;
    }
}
