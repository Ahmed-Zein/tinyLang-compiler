#include <bits/stdc++.h>
#include "./token/token.cpp"
#include "./lexer/lexer.cpp"

using namespace std;
int main()
{
    string t = " if end  09  \r\n\t";
    Lexer *l = new Lexer(t);
    Token *curToken= l->nextToken();
    cout<<curToken->type;
    curToken= l->nextToken();
    cout<<curToken->type;
    curToken= l->nextToken();
    cout<<curToken->type;
}