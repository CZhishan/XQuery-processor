grammar XQuery;
import XPath;

xq : var                                                         #variable
   | StringConstant                                              #string
   | ap                                                          #ap_xq
   | '(' xq ')'                                                  #brace_xq
   | xq '/' rp                                                   #single_xq
   | xq ',' xq                                                   #comma_xq
   | xq '//' rp                                                  #double_xq
   | startTag '{' xq '}' endTag                                  #tag_xq
   | forClause letClause? whereClause? returnClause              #flwr_clause
   | letClause xq                                                #let_clause
   | joinClause                                                  #join_clause
   ;

joinClause : 'join' '(' xq ',' xq ',' attrPair ',' attrPair ')';
forClause : 'for' var 'in' xq (',' var 'in' xq)* ;
letClause : 'let' var ':=' xq (',' var ':=' xq)* ;
whereClause : 'where' cond ;
returnClause : 'return' rt ;


cond : xq EQ xq                                                  #eq_cond
     | xq IS xq                                                  #is_cond
     | 'empty' '(' xq ')'                                        #empty_cond
     | 'some' var 'in' xq (',' var 'in' xq)* 'satisfies' cond    #satisfy_cond
     | '(' cond ')'                                              #brace_cond
     | cond 'and' cond                                           #and_cond
     | cond 'or' cond                                            #or_cond
     | 'not' cond                                                #not_cond
     ;

rt : xq                                                          #xq_return
   | rt ',' rt                                                   #comma_return
   | startTag rt endTag                                          #tag_return
   ;


startTag: '<' tagName '>';
endTag: '<' '/' tagName '>';
var: '$' ID;
StringConstant: '"'+[a-zA-Z0-9,.!?;: _'"-]+'"';

attrPair : '[' ID (',' ID)* ']';