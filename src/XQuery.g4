grammar XQuery;
import XPath;

xq : var                                                         #variable
   | StringConstant                                              #string
   | ap                                                          #ap_xq
   | '(' xq ')'                                                  #brace_xq
   | xq ',' xq                                                   #comma_xq
   | xq '/' rp                                                   #single_xq
   | xq '//' rp                                                  #double_xq
   | startTag '{' xq '}' endTag                                  #tag_xq
   | forClause letClause? whereClause? returnClause              #flwr_clause
   | letClause xq                                                #let_clause
   ;


forClause : 'for' var 'in' xq (',' var 'in' xq)* ;
letClause : 'let' var ':=' xq (',' var ':=' xq)* ;
whereClause : 'where' cond ;
returnClause : 'return' xq ;


cond : xq EQ xq                                                  #eq_cond
     | xq IS xq                                                  #is_cond
     | 'empty' '(' xq ')'                                        #empty_cond
     | 'some' var 'in' xq (',' var 'in' xq)* 'satisfies' cond    #satisfy_cond
     | '(' cond ')'                                              #brace_cond
     | cond 'and' cond                                           #and_cond
     | cond 'or' cond                                            #or_cond
     | 'not' cond                                                #not_cond
     ;

startTag: '<' tagName '>';
endTag: '<' '/' tagName '>';
var: '$' ID;
StringConstant: '"'+[a-zA-Z0-9,.!?;: _'"-]+'"';