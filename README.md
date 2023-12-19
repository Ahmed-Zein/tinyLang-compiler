# A recursive descent parser for the tiny language

## Tiny lang EBNF

- [x] program -> stmt-sequence
- [x] stmt-sequence -> statement { ; statement }
- [x] statement -> if-stmt | repeat-stmt | assign-stmt | read-stmt | write-stmt
- [x] if-stmt -> **if** exp then stmt-sequence [ **else** stmt-sequence ] **end**
- [x] repeat-stmt -> **repeat** stmt-sequence **until** exp
- [x] assign-stmt -> **identifier :=** exp
- [x] read-stmt -> **read identifier**
- [x] write-stmt -> **write** exp
- [x] exp -> simple-exp [ comparison-op simple-exp ]
- [x] comparison-op -> <|=
- [x] simple-exp -> term { addop term }
- [x] addop -> + | -
- [x] term -> factor { mulop factor }
- [x] mulop -> \* | /
- [x] factor -> **(** exp **)** | **number** | **identifier**

## TBD:

- [x] stmtSequence
- [x] comparisonOp,
- [x] repeatStmt,
- [x] assignStmt,
- [x] simpleExp,
- [x] statement,
- [x] writeStmt,
- [x] readStmt,
- [x] program,
- [x] factor,
- [x] ifStmt,
- [x] addop,
- [x] mulop,
- [x] term,
- [x] exp,

## ERRs

FIXME: missing producution rule causes no erroe (i.e) exp: {>}, this should not be valid

## Extra

- track line number to highlight the errors

```
some text
├─── more text
├─── and more
│    ├─── still more
│    ╰─── more
╰─── the end
```
