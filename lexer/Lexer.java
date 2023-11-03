package lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import token.Token;
import token.TokenType;

public class Lexer {
    String input;
    int position;
    int readPosition;
    char ch;

    Lexer() {
    }

    public Lexer(String input) {
        this.input = input;
    }

    public Token nexToken()  {
        readChar();
        skipWhiteSpaces();
        switch (this.ch) {
            case '{':
                skipCommit();
                return nexToken();
            case '(':
                return new Token(TokenType.LPAREN, ch);
            case ')':
                return new Token(TokenType.RPAREN, ch);
            case '+':
                return new Token(TokenType.PLUS, ch);
            case '-':
                return new Token(TokenType.MINUS, ch);
            case '<':
                if (Character.toString(peekChar()).compareTo("=") == 0) {
                    readChar();
                    return new Token(TokenType.LTorEQ, "<=");
                }
                return new Token(TokenType.LT, ch);
            case '>':
                if (Character.toString(peekChar()).compareTo("=") == 0) {
                    readChar();
                    return new Token(TokenType.GTorEQ, "<=");
                }
                return new Token(TokenType.GT, ch);
            case '=':
                // add equal operator??
                return new Token(TokenType.EQ, ch);
            case ';':
                return new Token(TokenType.SEMICOLON, ch);
            case ':':
                if (Character.toString(peekChar()).compareTo("=") == 0) {
                    readChar();
                    return new Token(TokenType.ASSINE, ":=");
                } else {
                    return new Token(TokenType.SYNTAX_ERROR,
                            "syntax error worng token at character" + "[" + this.readPosition + "], we got :"
                                    + this.peekChar() + "expected :=");
                }
            case 0:
                return new Token(TokenType.EOF, '0');
            default:
                Token tok = new Token();
                if (Character.isDigit(ch)) {
                    String num = readNumber();
                    tok = new Token(TokenType.NUMBER, num);
                } else if ('a' <= ch && ch <= 'z') {
                    String indntfier = this.readIdnetifier();
                    tok = new Token(Token.lookup(indntfier), indntfier);
                }
                System.out.println(Character.toString(input.charAt(position-1)));
                // readChar();
                return new Token(TokenType.SYNTAX_ERROR,"Err"+ch);
        }
    }

    void skipWhiteSpaces() {
        Pattern pattern = Pattern.compile(" |\t|\n|\r");
        Matcher matcher = pattern.matcher(Character.toString(ch));
        if (matcher.matches()) {
            this.readChar();
            skipWhiteSpaces();
        }
    }

    void readChar() {
        if (readPosition >= input.length()) {
            ch = 0;
            return;
        }
        ch = input.charAt(readPosition);
        this.position = this.readPosition;
        this.readPosition++;
    }

    char peekChar() {
        if (readPosition >= input.length()) {
            ch = 0;
            return 0;
        }
        return input.charAt(readPosition);
    }

    String readNumber() {
        int pos = this.position;
        while (Character.isDigit(peekChar())) {
            this.readChar();
        }
        return input.substring(pos, this.readPosition);
    }

    String readIdnetifier() {
        int pos = this.position;
        while ('a' <= peekChar() && peekChar() <= 'z') {
            this.readChar();
        }
        return input.substring(pos, this.readPosition);
    }

    void skipCommit() {
        System.out.println("comment"+ch);
        while (Character.toString(peekChar()).compareTo("}") != 0) {
            readChar();
            if (this.readPosition >= this.input.length()) {
                return;
            }
        }
        readChar();
    }
}
