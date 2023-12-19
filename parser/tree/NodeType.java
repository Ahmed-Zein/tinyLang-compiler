package parser.tree;

public enum NodeType {
    stmtSequence,
    comparisonOp,
    repeatStmt,
    assignStmt,
    simpleExp,
    statement,
    writeStmt,
    readStmt,
    program,
    factor,
    ifStmt,
    addop,
    mulop,
    term,
    exp,
    eof,

    // err reovery
    undefinedstmt,
    unexpectedToken,
}