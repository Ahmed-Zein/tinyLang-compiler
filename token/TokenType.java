package token;

public enum TokenType {
    EOF,
    SYNTAX_ERROR,
    // identifire + literal
    IDENTIFIER,
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
    OPENBRACKET,
    CLOSEDBRACKET,

    // operators
    PLUS,
    MINUS,
    MULT,
    DIV,
    LT,
    GT,
    GTorEQ,
    LTorEQ,
    EQ,
    ASSINE,
}
