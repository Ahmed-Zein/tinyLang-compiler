package token;

public enum TokenType {
    EOF,
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
    MULOP,
    DIV,
    LT,
    GT,
    GTorEQ,
    LTorEQ,
    EQ,
    ASSINE,

    // error recovery tokens
    SYNTAX_ERROR,
    UNEXCEPTED_TOKEN,
}
