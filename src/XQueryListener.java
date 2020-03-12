// Generated from E:/CHEN Zhishan/Documents/20winter/database/project1/src\XQuery.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryParser}.
 */
public interface XQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code let_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterLet_clause(XQueryParser.Let_clauseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitLet_clause(XQueryParser.Let_clauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ap_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterAp_xq(XQueryParser.Ap_xqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ap_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitAp_xq(XQueryParser.Ap_xqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterString(XQueryParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitString(XQueryParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code double_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterDouble_xq(XQueryParser.Double_xqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code double_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitDouble_xq(XQueryParser.Double_xqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comma_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterComma_xq(XQueryParser.Comma_xqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comma_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitComma_xq(XQueryParser.Comma_xqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brace_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterBrace_xq(XQueryParser.Brace_xqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brace_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitBrace_xq(XQueryParser.Brace_xqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tag_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterTag_xq(XQueryParser.Tag_xqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tag_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitTag_xq(XQueryParser.Tag_xqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterVariable(XQueryParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitVariable(XQueryParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code join_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterJoin_clause(XQueryParser.Join_clauseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code join_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitJoin_clause(XQueryParser.Join_clauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterSingle_xq(XQueryParser.Single_xqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitSingle_xq(XQueryParser.Single_xqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code flwr_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterFlwr_clause(XQueryParser.Flwr_clauseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code flwr_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitFlwr_clause(XQueryParser.Flwr_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void enterJoinClause(XQueryParser.JoinClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void exitJoinClause(XQueryParser.JoinClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code is_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterIs_cond(XQueryParser.Is_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code is_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitIs_cond(XQueryParser.Is_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code empty_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_cond(XQueryParser.Empty_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code empty_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_cond(XQueryParser.Empty_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterEq_cond(XQueryParser.Eq_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eq_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitEq_cond(XQueryParser.Eq_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterAnd_cond(XQueryParser.And_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitAnd_cond(XQueryParser.And_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code satisfy_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterSatisfy_cond(XQueryParser.Satisfy_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code satisfy_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitSatisfy_cond(XQueryParser.Satisfy_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brace_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterBrace_cond(XQueryParser.Brace_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brace_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitBrace_cond(XQueryParser.Brace_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterOr_cond(XQueryParser.Or_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitOr_cond(XQueryParser.Or_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterNot_cond(XQueryParser.Not_condContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitNot_cond(XQueryParser.Not_condContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 */
	void enterXq_return(XQueryParser.Xq_returnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 */
	void exitXq_return(XQueryParser.Xq_returnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tag_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 */
	void enterTag_return(XQueryParser.Tag_returnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tag_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 */
	void exitTag_return(XQueryParser.Tag_returnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comma_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 */
	void enterComma_return(XQueryParser.Comma_returnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comma_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 */
	void exitComma_return(XQueryParser.Comma_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#startTag}.
	 * @param ctx the parse tree
	 */
	void enterStartTag(XQueryParser.StartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#startTag}.
	 * @param ctx the parse tree
	 */
	void exitStartTag(XQueryParser.StartTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#endTag}.
	 * @param ctx the parse tree
	 */
	void enterEndTag(XQueryParser.EndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#endTag}.
	 * @param ctx the parse tree
	 */
	void exitEndTag(XQueryParser.EndTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(XQueryParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(XQueryParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#attrPair}.
	 * @param ctx the parse tree
	 */
	void enterAttrPair(XQueryParser.AttrPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#attrPair}.
	 * @param ctx the parse tree
	 */
	void exitAttrPair(XQueryParser.AttrPairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single_ap}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterSingle_ap(XQueryParser.Single_apContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_ap}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitSingle_ap(XQueryParser.Single_apContext ctx);
	/**
	 * Enter a parse tree produced by the {@code double_ap}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterDouble_ap(XQueryParser.Double_apContext ctx);
	/**
	 * Exit a parse tree produced by the {@code double_ap}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitDouble_ap(XQueryParser.Double_apContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(XQueryParser.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(XQueryParser.DocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code att_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAtt_rp(XQueryParser.Att_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code att_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAtt_rp(XQueryParser.Att_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterFilter_rp(XQueryParser.Filter_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitFilter_rp(XQueryParser.Filter_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parent_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParent_rp(XQueryParser.Parent_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parent_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParent_rp(XQueryParser.Parent_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code self_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSelf_rp(XQueryParser.Self_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code self_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSelf_rp(XQueryParser.Self_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brace_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterBrace_rp(XQueryParser.Brace_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brace_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitBrace_rp(XQueryParser.Brace_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tag_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTag_rp(XQueryParser.Tag_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tag_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTag_rp(XQueryParser.Tag_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code text_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterText_rp(XQueryParser.Text_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code text_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitText_rp(XQueryParser.Text_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comma_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterComma_rp(XQueryParser.Comma_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comma_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitComma_rp(XQueryParser.Comma_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSingle_rp(XQueryParser.Single_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSingle_rp(XQueryParser.Single_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code children_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterChildren_rp(XQueryParser.Children_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code children_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitChildren_rp(XQueryParser.Children_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code double_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterDouble_rp(XQueryParser.Double_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code double_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitDouble_rp(XQueryParser.Double_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterRp_flt(XQueryParser.Rp_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitRp_flt(XQueryParser.Rp_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brace_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterBrace_flt(XQueryParser.Brace_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brace_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitBrace_flt(XQueryParser.Brace_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterNot_flt(XQueryParser.Not_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitNot_flt(XQueryParser.Not_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEq_flt(XQueryParser.Eq_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eq_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEq_flt(XQueryParser.Eq_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code is_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterIs_flt(XQueryParser.Is_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code is_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitIs_flt(XQueryParser.Is_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterAnd_flt(XQueryParser.And_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitAnd_flt(XQueryParser.And_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterOr_flt(XQueryParser.Or_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitOr_flt(XQueryParser.Or_fltContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#attName}.
	 * @param ctx the parse tree
	 */
	void enterAttName(XQueryParser.AttNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#attName}.
	 * @param ctx the parse tree
	 */
	void exitAttName(XQueryParser.AttNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(XQueryParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(XQueryParser.FilenameContext ctx);
}