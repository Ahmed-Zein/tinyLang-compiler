# A recursive descent parser for the tiny language

## Tiny lang EBNF

- program -> stmt-sequence
- stmt-sequence -> statement { ; statement }
- statement -> if-stmt | repeat-stmt | assign-stmt | read-stmt | write-stmt
- if-stmt -> **if** exp then stmt-sequence [ **else** stmt-sequence ] **end**
- repeat-stmt -> **repeat** stmt-sequence **until** exp
- assign-stmt -> **identifier :=** exp
- read-stmt -> **read identifier**
- write-stmt -> **write** exp
- exp -> simple-exp [ comparison-op simple-exp ]
- comparison-op -> <|=
- simple-exp -> term { addop term }
- addop -> + | -
- term -> factor { mulop factor }
- mulop -> \* | /
- factor -> **(** exp **)** | **number** | **identifier**

## TBD:

- [ ] stmtSequence
- [ ] comparisonOp,
- [ ] repeatStmt,
- [ ] assignStmt,
- [ ] simpleExp,
- [ ] statement,
- [ ] writeStmt,
- [ ] readStmt,
- [ ] program,
- [x] factor,
- [ ] ifStmt,
- [ ] addop,
- [ ] mulop,
- [ ] term,
- [ ] exp,

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
