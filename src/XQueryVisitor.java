// Generated from E:/CHEN Zhishan/Documents/20winter/database/project1/src\XQuery.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code let_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_clause(XQueryParser.Let_clauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ap_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAp_xq(XQueryParser.Ap_xqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(XQueryParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_xq(XQueryParser.Double_xqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comma_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComma_xq(XQueryParser.Comma_xqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brace_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace_xq(XQueryParser.Brace_xqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tag_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_xq(XQueryParser.Tag_xqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(XQueryParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code join_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_clause(XQueryParser.Join_clauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single_xq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_xq(XQueryParser.Single_xqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code flwr_clause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlwr_clause(XQueryParser.Flwr_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#joinClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinClause(XQueryParser.JoinClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code is_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_cond(XQueryParser.Is_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code empty_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty_cond(XQueryParser.Empty_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_cond(XQueryParser.Eq_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_cond(XQueryParser.And_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code satisfy_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSatisfy_cond(XQueryParser.Satisfy_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brace_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace_cond(XQueryParser.Brace_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_cond(XQueryParser.Or_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not_cond}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_cond(XQueryParser.Not_condContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_return(XQueryParser.Xq_returnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tag_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_return(XQueryParser.Tag_returnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comma_return}
	 * labeled alternative in {@link XQueryParser#rt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComma_return(XQueryParser.Comma_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#startTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartTag(XQueryParser.StartTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#endTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndTag(XQueryParser.EndTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(XQueryParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#attrPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrPair(XQueryParser.AttrPairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single_ap}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_ap(XQueryParser.Single_apContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double_ap}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_ap(XQueryParser.Double_apContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(XQueryParser.DocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code att_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtt_rp(XQueryParser.Att_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_rp(XQueryParser.Filter_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parent_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParent_rp(XQueryParser.Parent_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code self_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelf_rp(XQueryParser.Self_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brace_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace_rp(XQueryParser.Brace_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tag_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_rp(XQueryParser.Tag_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code text_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText_rp(XQueryParser.Text_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comma_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComma_rp(XQueryParser.Comma_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_rp(XQueryParser.Single_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code children_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildren_rp(XQueryParser.Children_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double_rp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_rp(XQueryParser.Double_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_flt(XQueryParser.Rp_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brace_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace_flt(XQueryParser.Brace_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_flt(XQueryParser.Not_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_flt(XQueryParser.Eq_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code is_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_flt(XQueryParser.Is_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_flt(XQueryParser.And_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or_flt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_flt(XQueryParser.Or_fltContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#tagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#attName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttName(XQueryParser.AttNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(XQueryParser.FilenameContext ctx);
}