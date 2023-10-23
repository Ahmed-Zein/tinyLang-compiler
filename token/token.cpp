#ifndef TOKEN
#define TOKEN
#include <bits/stdc++.h>

using namespace std;
typedef string TokenType;

class Token
{
public:
  string type;
  string literal;
  Token() {}
  Token(string type, char literal) {
    this->literal = to_string(literal);
    this->type = type;
  }

private:
};

// identifire + literal
const string IDENT = "IDENT";
const string NUMBER = "NUMBER";

// keywords
const string IF = "if";
const string THEN = "then";
const string ELSE = "else";
const string END = "end";
const string REPEAT = "reapte";
const string UNTIL = "until";
const string READ = "read";
const string WRITE = "write";

// delimiters
const string SEMICOLON = ";";
const string LBRACE = "{";
const string RBRACE = "}";
const string LPAREN = "(";
const string RPAREN = ")";

// operators
const string PLUS = "+";
const string MINUS = "-";
const string ASTERISK = "-";
const string SLASH = "/";
const string LT = "<";
const string GT = ">";
const string EQ = "=";
const string ASSINE = ":=";

string lookup(string k)
{
  map<string, string> keywords;
  keywords["if"] = IF;
  keywords["then"] = THEN;
  keywords["else"] = ELSE;
  keywords["end"] = END;
  keywords["repeat"] = REPEAT;
  keywords["until"] = UNTIL;
  keywords["read"] = READ;
  keywords["write"] = WRITE;
  return keywords[k];
};
#endif