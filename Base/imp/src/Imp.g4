grammar Imp;

prog : com EOF ;

com : FUN ID LPAR fun_args RPAR LBRACE com RETURN exp RBRACE            # fun
    | IF LPAR exp RPAR THEN LBRACE com RBRACE ELSE LBRACE com RBRACE    # if
    | ID ASSIGN exp                                                     # assign
    | SKIPP                                                             # skip
    | com SEMICOLON com                                                 # seq
    | WHILE LPAR exp RPAR LBRACE com RBRACE                             # while
    | OUT LPAR exp RPAR                                                 # out
    ;

fun_args :
         | ID
         | ID COMMA ID
         ;

exp : NAT                                 # nat
    | BOOL                                # bool
    | LPAR exp RPAR                       # parExp
    | <assoc=right> exp POW exp           # pow
    | NOT exp                             # not
    | exp op=(DIV | MUL | MOD) exp        # divMulMod
    | exp op=(PLUS | MINUS) exp           # plusMinus
    | exp op=(LT | LEQ | GEQ | GT) exp    # cmpExp
    | exp op=(EQQ | NEQ) exp              # eqExp
    | exp op=(AND | OR) exp               # logicExp
    | ID                                  # id
    ;

NAT : '0' | [1-9][0-9]* ;
BOOL : 'true' | 'false' ;

PLUS  : '+' ;
MINUS : '-';
MUL   : '*' ;
DIV   : '/' ;
MOD   : 'mod' ;
POW   : '^' ;

AND : '&' ;
OR  : '|' ;

EQQ : '==' ;
NEQ : '!=' ;
LEQ : '<=' ;
GEQ : '>=' ;
LT  : '<' ;
GT  : '>' ;
NOT : '!' ;

IF     : 'if' ;
THEN   : 'then' ;
ELSE   : 'else' ;
WHILE  : 'while' ;
SKIPP  : 'skip' ;
ASSIGN : '=' ;
OUT    : 'out' ;
FUN    : 'fun' ;
RETURN : 'return' ;

LPAR      : '(' ;
RPAR      : ')';
LBRACE    : '{' ;
RBRACE    : '}' ;
SEMICOLON : ';' ;
COMMA     : ',' ;

ID : [a-z]+ ;

WS : [ \t\r\n]+ -> skip ;