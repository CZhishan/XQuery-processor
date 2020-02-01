// Generated from E:/CHEN Zhishan/Documents/20winter/database/project1/src\XPath.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XPathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XPathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code single_ap}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_ap(XPathParser.Single_apContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double_ap}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_ap(XPathParser.Double_apContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(XPathParser.DocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code att_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtt_rp(XPathParser.Att_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_rp(XPathParser.Filter_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parent_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParent_rp(XPathParser.Parent_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code self_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelf_rp(XPathParser.Self_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brace_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace_rp(XPathParser.Brace_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tag_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag_rp(XPathParser.Tag_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code text_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText_rp(XPathParser.Text_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comma_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComma_rp(XPathParser.Comma_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_rp(XPathParser.Single_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code children_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildren_rp(XPathParser.Children_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_rp(XPathParser.Double_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_flt(XPathParser.Rp_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brace_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace_flt(XPathParser.Brace_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_flt(XPathParser.Not_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_flt(XPathParser.Eq_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code is_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_flt(XPathParser.Is_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_flt(XPathParser.And_fltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_flt(XPathParser.Or_fltContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#tagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(XPathParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#attName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttName(XPathParser.AttNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(XPathParser.FilenameContext ctx);
}