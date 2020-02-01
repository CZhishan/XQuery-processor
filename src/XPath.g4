grammar XPath;

//absolute path
ap : doc  '/' rp     #single_ap
   | doc  '//' rp    #double_ap
   ;

//filename
doc: 'doc("' filename '")'
   | 'document("' filename '")'
   ;

//relative path
rp : tagName                   #tag_rp
   | '*'                       #children_rp
   | '.'                       #self_rp
   | '..'                      #parent_rp
   | 'text()'                  #text_rp
   | '@' attName               #att_rp
   |  rp ',' rp                #comma_rp
   | '(' rp ')'                #brace_rp
   | rp '/' rp                 #single_rp
   | rp '//' rp                #double_rp
   | rp '[' f ']'              #filter_rp
   ; 

//filters
f : rp                         #rp_flt
  | rp EQ rp                   #eq_flt
  | rp IS rp                   #is_flt
  | '(' f ')'                  #brace_flt
  | f 'and' f                  #and_flt
  | f 'or' f                   #or_flt
  | 'not' f                    #not_flt
  ;

tagName:  ID;
attName:  ID;

EQ: '=' | 'eq';
IS: '==' | 'is';
ID: [a-zA-Z0-9_-]+ ;

filename: FILENAME;
FILENAME: [a-zA-Z0-9._]+;

WS: [ \t\n\r]+ -> skip;
