/* *** This file is given as part of the programming assignment. *** */

// Token Kind (internal representations of tokens)

public enum TK {

    VAR,     // var
    RAV,     // rav
    PRINT,   // print
    SKIP,    // skip
    STOP,	 // stop
    IF,      // if
    FI,      // fi
    DO,      // do
    OD,      // od
    ELSE,    // else
    FA,      // fa
    AF,      // af
    TO,      // to
    ST,      // st
    MODULO,  // mod
    MAX,     // max
    BREAK,   // break

    ASSIGN,   // :=
    LPAREN,   // (
    RPAREN,   // )
    PLUS,     // +
    MINUS,    // -
    TIMES,    // *
    DIVIDE,   // /
    REMAINDER,// %
    COMMA,    // ,

    SQUARE,   // ^
    SQRT,     // @

    EQ,       // =
    NE,       // /=
    LT,       // <
    GT,       // >
    LE,       // <=
    GE,       // >=

    ARROW,    // ->
    BOX,      // []


    ID,       // identifier

    NUM,      // number

    EOF,      // end of file

    // ERROR special error token kind (for scanner to return to parser)
    ERROR,
    // none marks end of each first set in parsing.
    // you might not need this.
    none,
}
