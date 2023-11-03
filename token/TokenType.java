package token;

public enum TokenType {
    EOF,
    SYNTAX_ERROR,
    // identifire + literal
    IDENT,
    NUMBER,

    // keywords
    IF,
    THEN,
    ELSE,
    END,
    REPEAT,
    UNTIL,
    READ,
    WRITE,

    // delimiters
    SEMICOLON,
    LBRACE,
    RBRACE,
    LPAREN,
    RPAREN,

    // operators
    PLUS,
    MINUS,
    ASTERISK,
    DIV,
    LT,
    GT,
    GTorEQ,
    LTorEQ,
    EQ,
    ASSINE,
}
