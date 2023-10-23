#include <bits/stdc++.h>
#include "../token/token.cpp"
#include <regex>

using namespace std;

bool isDigit(char ch);
bool isLetter(char ch);

class Lexer
{
public:
    int position;
    int readPosition;
    char ch;
    Lexer() {}
    Lexer(string input) : input(input)
    {
        position = 0;
        readPosition = 0;
        readChar();
    }
    void skipWhiteSpaces();
    void readChar();
    char peekChar();
    string readNumber();
    string readIdentifier();
    Token *nextToken();

private:
    string input;
};

void Lexer::readChar()
{
    if (readPosition >= input.size())
    {
        ch = 0;
    }
    else
    {
        ch = input[readPosition];
    }
    position = readPosition;
    readPosition += 1;
}

Token *Lexer::nextToken()
{
    Token *tok;
    skipWhiteSpaces();
    switch (ch)
    {
    case '(':
        tok = new Token(LPAREN, ch);
        break;
    case ')':
        tok = new Token(RPAREN, ch);
        break;
    case '+':
        tok = new Token(PLUS, ch);
        break;
    case '-':
        tok = new Token(MINUS, ch);
        break;
    case '<':
        tok = new Token(LT, ch);
        break;
    case '>':
        tok = new Token(GT, ch);
        break;
    case '=':
        // add equal operator??
        tok = new Token(EQ, ch);
        break;
    case ';':
        tok = new Token(SEMICOLON, ch);
        break;
    case ':':
        tok = new Token();
        if (peekChar() == '=')
        {
            tok->literal = ":=";
            tok->type = ASSINE;
            readChar();
        }
        else
        {
            // TODO: should flag a syntax error
        }
        break;
    case 0:
        cout << "end fo file";
        break;
    default:
        tok = new Token();
        if (isDigit(ch))
        {
            tok->type = NUMBER;
            tok->literal = readNumber();
        }
        else if (isLetter(ch))
        {
            tok->type = IDENT;
            tok->literal = readIdentifier();
        }
        break;
    }
    return tok;
}

void Lexer::skipWhiteSpaces()
{
    while (ch == '\r' || ch == '\t' || ch == '\n' || ch == ' ')
    {
        readChar();
    }
    return;
}

char Lexer::peekChar()
{
    if (readPosition >= input.size())
    {
        return 0;
    }
    return input[readPosition];
}

string Lexer::readNumber()
{
    int startIdx = position;
    while (isDigit(ch))
    {
        readChar();
    }
    return input.substr(startIdx, readPosition);
}
string Lexer::readIdentifier()
{
    int startIdx = position;
    while (isLetter(ch))
    {
        readChar();
    }
    return input.substr(startIdx, readPosition);
}

bool isDigit(char ch)
{
    return (ch >= '0' && ch <= '9');
}
bool isLetter(char ch)
{
    return (ch >= 'a' && ch <= 'z');
}
