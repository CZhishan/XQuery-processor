// Generated from E:/CHEN Zhishan/Documents/20winter/database/project1/src\XPath.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code single_ap}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterSingle_ap(XPathParser.Single_apContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_ap}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitSingle_ap(XPathParser.Single_apContext ctx);
	/**
	 * Enter a parse tree produced by the {@code double_ap}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterDouble_ap(XPathParser.Double_apContext ctx);
	/**
	 * Exit a parse tree produced by the {@code double_ap}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitDouble_ap(XPathParser.Double_apContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(XPathParser.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(XPathParser.DocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code att_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAtt_rp(XPathParser.Att_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code att_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAtt_rp(XPathParser.Att_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterFilter_rp(XPathParser.Filter_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitFilter_rp(XPathParser.Filter_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parent_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParent_rp(XPathParser.Parent_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parent_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParent_rp(XPathParser.Parent_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code self_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSelf_rp(XPathParser.Self_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code self_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSelf_rp(XPathParser.Self_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brace_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterBrace_rp(XPathParser.Brace_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brace_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitBrace_rp(XPathParser.Brace_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tag_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTag_rp(XPathParser.Tag_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tag_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTag_rp(XPathParser.Tag_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code text_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterText_rp(XPathParser.Text_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code text_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitText_rp(XPathParser.Text_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comma_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterComma_rp(XPathParser.Comma_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comma_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitComma_rp(XPathParser.Comma_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterSingle_rp(XPathParser.Single_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitSingle_rp(XPathParser.Single_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code children_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterChildren_rp(XPathParser.Children_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code children_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitChildren_rp(XPathParser.Children_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code double_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterDouble_rp(XPathParser.Double_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code double_rp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitDouble_rp(XPathParser.Double_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterRp_flt(XPathParser.Rp_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitRp_flt(XPathParser.Rp_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brace_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterBrace_flt(XPathParser.Brace_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brace_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitBrace_flt(XPathParser.Brace_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterNot_flt(XPathParser.Not_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitNot_flt(XPathParser.Not_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEq_flt(XPathParser.Eq_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eq_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEq_flt(XPathParser.Eq_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code is_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterIs_flt(XPathParser.Is_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code is_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitIs_flt(XPathParser.Is_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterAnd_flt(XPathParser.And_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitAnd_flt(XPathParser.And_fltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterOr_flt(XPathParser.Or_fltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or_flt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitOr_flt(XPathParser.Or_fltContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XPathParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XPathParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#attName}.
	 * @param ctx the parse tree
	 */
	void enterAttName(XPathParser.AttNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#attName}.
	 * @param ctx the parse tree
	 */
	void exitAttName(XPathParser.AttNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(XPathParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(XPathParser.FilenameContext ctx);
}