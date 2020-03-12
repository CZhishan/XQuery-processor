import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;


public class myXQueryVisitor extends XQueryBaseVisitor<LinkedList> {
    private HashMap<String, LinkedList<Node>> contextMap = new HashMap<>();
    private Stack<HashMap<String, LinkedList<Node>>> contextStack = new Stack<>();
    Document outputDoc = null;
    private Document doc = null;
    boolean reFlag = true;

    public myXQueryVisitor(){
        try {
            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            outputDoc = docB.newDocument();
            doc = docB.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private Node makeElem(String tag, LinkedList<Node> list){
        Node newElement = outputDoc.createElement(tag);
        for (Node node : list) {
            if (node != null) {
                Node newNode = outputDoc.importNode(node, true);
                newElement.appendChild(newNode);
            }
        }
        return newElement;
    }

    private Node makeText(String s){
        Node newText = doc.createTextNode(s);
        return newText;
    }

    public static LinkedList<Node> nodeChildren(Node curNode){
        LinkedList<Node> childrenList = new LinkedList<>();
        for (int i = 0; i < curNode.getChildNodes().getLength(); i++) {
            childrenList.add(curNode.getChildNodes().item(i));
        }
        return childrenList;
    }

    private HashMap joinLeft(LinkedList<Node> tupleList, String [] hashAtts){
        HashMap<String, LinkedList<Node>> results = new HashMap<>();
        for (Node tuple: tupleList){
            LinkedList<Node> children = nodeChildren(tuple);
            String key = "";
            for (String hashAtt: hashAtts) {
                for (Node child: children){
                    if (hashAtt.equals(child.getNodeName()))
                        key += child.getFirstChild().getTextContent();
                }
            }
            if (results.containsKey(key))
                results.get(key).add(tuple);
            else{
                LinkedList<Node> value = new LinkedList<>();
                value.add(tuple);
                results.put(key, value);
            }
        }
        return results;
    }

    private LinkedList<Node> joinRight(HashMap<String, LinkedList<Node>> hashMapOnLeft, LinkedList<Node> right, String [] attrRight){
        LinkedList<Node> results = new LinkedList<>();
        for (Node tuple: right){
            LinkedList<Node> children = nodeChildren(tuple);
            String key = "";
            for (String hashAtt: attrRight) {
                for (Node child: children){
                    if (hashAtt.equals(child.getNodeName())) {
                        key += child.getFirstChild().getTextContent();
                    }
                }
            }

            if (hashMapOnLeft.containsKey(key))
                results.addAll(product(hashMapOnLeft.get(key),tuple));
        }
        return results;
    }
    private LinkedList<Node> product(LinkedList<Node> leftList, Node right){
        LinkedList<Node> result = new LinkedList<>();
        for (Node left: leftList){
            LinkedList<Node> newTupleChildren = nodeChildren(left);
            newTupleChildren.addAll(nodeChildren(right));
            result.add(makeElem("tuple", newTupleChildren));
        }
        return result;
    }

    @Override public LinkedList<Node> visitJoin_clause(XQueryParser.Join_clauseContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitJoinClause(XQueryParser.JoinClauseContext ctx) {
        LinkedList<Node> left = visit(ctx.xq(0));
        LinkedList<Node> right = visit(ctx.xq(1));
        int idSize = ctx.attrPair(0).ID().size();
        String [] attrLeft = new String [idSize];
        String [] attrRight = new String [idSize];
        for (int i = 0; i < idSize; i++){
            attrLeft[i] = ctx.attrPair(0).ID(i).getText();
            attrRight[i] = ctx.attrPair(1).ID(i).getText();
        }
        HashMap<String, LinkedList<Node>> hashMapOnLeft = joinLeft(left, attrLeft);
        LinkedList<Node> result = joinRight(hashMapOnLeft, right, attrRight);

        return result;
    }

    @Override public LinkedList<Node> visitLet_clause(XQueryParser.Let_clauseContext ctx) {
        HashMap<String, LinkedList<Node>> old_ctx = new HashMap<>(contextMap);
        contextStack.push(old_ctx);
        LinkedList<Node> results = visitChildren(ctx);
        contextMap = contextStack.pop();
        return results;
    }

    @Override public LinkedList<Node> visitAp_xq(XQueryParser.Ap_xqContext ctx) {
        String ap = ctx.getText();
        LinkedList<Node> results = testXPath.evaluateAp(ap);
        return results;

    }

    @Override public LinkedList<Node> visitString(XQueryParser.StringContext ctx) {
        String str = ctx.StringConstant().getText();
        int len = str.length();
        str = str.substring(1,len-1);

        Node node = makeText(str);
        LinkedList<Node> results = new LinkedList<>();
        results.add(node);
        return results;
    }

    @Override public LinkedList<Node> visitDouble_xq(XQueryParser.Double_xqContext ctx) {
        LinkedList<Node> currentNodes = new LinkedList<>();
        LinkedList<Node> ctxNodes =  visit(ctx.xq());
        currentNodes.addAll(ctxNodes);

        LinkedList<Node> descendants = myXPathVisitor.getDescendants(currentNodes);
        currentNodes.addAll(descendants);
        LinkedList<Node> results = testXPath.evaluateRp(currentNodes, ctx.rp().getText());
        return results;
    }

    @Override public LinkedList<Node> visitComma_xq(XQueryParser.Comma_xqContext ctx) {
        LinkedList<Node> results = new LinkedList<>();
        results.addAll(visit(ctx.xq(0)));
        results.addAll(visit(ctx.xq(1)));
        return results;
    }

    @Override public LinkedList<Node> visitBrace_xq(XQueryParser.Brace_xqContext ctx) {
        return visit(ctx.xq());
    }

    @Override public LinkedList<Node> visitTag_xq(XQueryParser.Tag_xqContext ctx) {
        String tagName = ctx.startTag().tagName().getText();
        LinkedList<Node> ctxNodes = visit(ctx.xq());
        Node node = makeElem(tagName, ctxNodes);
        LinkedList<Node> results = new  LinkedList<>();
        results.add(node);
        return results;
    }

    @Override public LinkedList<Node> visitVariable(XQueryParser.VariableContext ctx) {
        return contextMap.get(ctx.getText());
    }

    @Override public LinkedList<Node> visitSingle_xq(XQueryParser.Single_xqContext ctx) {
        LinkedList<Node> currentNodes = new LinkedList<>();
        LinkedList<Node> ctxNodes =  visit(ctx.xq());
        currentNodes.addAll(ctxNodes);

        LinkedList<Node> results = testXPath.evaluateRp(currentNodes, ctx.rp().getText());
        return results;
    }

    private void FLWR_helper(int k, LinkedList<Node> results, XQueryParser.Flwr_clauseContext ctx){
        int numFor = ctx.forClause().var().size();
        if (k == numFor){
            if (ctx.letClause() != null) {
                visit(ctx.letClause());
            }
            if (ctx.whereClause() != null) {
                if (visit(ctx.whereClause()).size() == 0) return;
            }
            LinkedList<Node> c = visit(ctx.returnClause());
            if (c != null) {
                results.addAll(visit(ctx.returnClause()));
            }
        }
        else{
            String key = ctx.forClause().var(k).getText();
            LinkedList<Node> valueList = visit(ctx.forClause().xq(k));
            for (Node node: valueList){
                HashMap<String, LinkedList<Node>> old_ctx = new HashMap<>(contextMap);
                contextStack.push(old_ctx);
                LinkedList<Node> value = new LinkedList<>();
                value.add(node);
                contextMap.put(key, value);
                if (k+1 <= numFor)
                    FLWR_helper(k+1, results, ctx);
                contextMap = contextStack.pop();
            }
        }
    }

    @Override public LinkedList<Node> visitFlwr_clause(XQueryParser.Flwr_clauseContext ctx) {
        LinkedList<Node> results = new LinkedList<>();

        HashMap<String, LinkedList<Node>> old_ctx = new HashMap<>(contextMap);
        contextStack.push(old_ctx);

        if (!reFlag || ctx.forClause().xq(0).getText().startsWith("join")){
            FLWR_helper(0, results, ctx);
        }
        else{
            Rewriter re = new Rewriter();
            String rewrited = re.Rewriting(ctx);
            if (rewrited  == ""){
                FLWR_helper(0, results, ctx);
            }
            else {
                results = testXquery.evalRewrited(rewrited);
            }
        }

        contextMap = contextStack.pop();
        return results;
    }

    private LinkedList<Node> for_helper(int k, XQueryParser.ForClauseContext ctx){
        LinkedList<Node> result = new LinkedList<>();
        LinkedList<Node> tempList = visit(ctx.xq(k));

        if(ctx.xq().size() == 1) {
            for(Node temp: tempList) {
                LinkedList<Node> value = new LinkedList<>();
                value.add(temp);
                contextMap.put(ctx.var(k).getText(), value);
                result.add(temp);
            }
            return result;
        }
        else {
            for(Node temp: tempList) {
                HashMap<String, LinkedList<Node>> old_ctx = new HashMap<>(contextMap);
                contextStack.push(old_ctx);
                LinkedList<Node> value = new LinkedList<>();
                value.add(temp);
                contextMap.put(ctx.var(k).getText(), value);
                result.addAll(for_helper(k + 1, ctx));
                contextMap = contextStack.pop();
            }
            return result;
        }
    }


    @Override public LinkedList<Node> visitForClause(XQueryParser.ForClauseContext ctx) {
        LinkedList<Node> results = new LinkedList<>();
        results.addAll(for_helper(0, ctx));
        return results;
    }

    @Override public LinkedList<Node> visitLetClause(XQueryParser.LetClauseContext ctx) {
        for (int i = 0; i < ctx.var().size(); i++) {
            String key = ctx.var(i).getText();
            LinkedList<Node> value = visit(ctx.xq(i));
            contextMap.put(key, value);
        }
        return null;
    }

    @Override public LinkedList<Node> visitWhereClause(XQueryParser.WhereClauseContext ctx) {
        return visit(ctx.cond());
    }


    @Override public LinkedList<Node> visitReturnClause(XQueryParser.ReturnClauseContext ctx) {
        return visit(ctx.rt());
    }

    @Override public LinkedList<Node> visitXq_return(XQueryParser.Xq_returnContext ctx) { return visit(ctx.xq()); }

    @Override public LinkedList<Node> visitTag_return(XQueryParser.Tag_returnContext ctx) {
        String tagName = ctx.startTag().tagName().getText();
        LinkedList<Node> nodeList = visit(ctx.rt());
        Node node = makeElem(tagName, nodeList);
        LinkedList<Node> results = new  LinkedList<>();
        results.add(node);
        return results;

    }

    @Override public LinkedList<Node> visitComma_return(XQueryParser.Comma_returnContext ctx) {
        LinkedList<Node> results = visit(ctx.rt(0));
        results.addAll(visit(ctx.rt(1)));
        return results;
    }

    @Override public LinkedList<Node> visitIs_cond(XQueryParser.Is_condContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.xq(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.xq(1)));
        LinkedList<Node> results = new LinkedList<>();

        for (Node l: left)
            for (Node r: right)
                if (l.isSameNode(r)){
                    Node True = doc.createTextNode("true");
                    results.add(True);
                    return results;
                }
        return results;
    }

    @Override public LinkedList<Node> visitEmpty_cond(XQueryParser.Empty_condContext ctx) {
        LinkedList<Node> ctxNodes = visit(ctx.xq());
        LinkedList<Node> results = new LinkedList<>();

        if (ctxNodes.isEmpty()){
            Node n = doc.createTextNode("true");
            results.add(n);
        }
        return results;
    }

    @Override public LinkedList<Node> visitEq_cond(XQueryParser.Eq_condContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.xq(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.xq(1)));
        LinkedList<Node> results = new LinkedList<>();

        for (Node l: left)
            for (Node r: right)
                if (l.isEqualNode(r)) {
                    Node n = doc.createTextNode("true");
                    results.add(n);
                    return results;
                }
        return results;
    }

    @Override public LinkedList<Node> visitAnd_cond(XQueryParser.And_condContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.cond(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.cond(1)));
        LinkedList<Node> results = new LinkedList<>();

        if (left.size() > 0 && right.size() > 0){
            Node n = doc.createTextNode("true");
            results.add(n);
        }
        return results;
    }

    private boolean satisfy_helper(int k, XQueryParser.Satisfy_condContext ctx){

        int numFor = ctx.var().size();
        if (k == numFor){
            if (visit(ctx.cond()).size() == 1)
                return true;
        }
        else{
            String key = ctx.var(k).getText();
            LinkedList<Node> valueList = visit(ctx.xq(k));

            for (Node node: valueList){
                HashMap<String, LinkedList<Node>> old_ctx = new HashMap<>(contextMap);
                contextStack.push(old_ctx);

                LinkedList<Node> value = new LinkedList<>();
                value.add(node);
                contextMap.put(key, value);
                if (k+1 <= numFor)
                    if (satisfy_helper(k+1, ctx)) {
                        contextMap = contextStack.pop();
                        return true;
                    }
                contextMap = contextStack.pop();
            }
        }
        return false;
    }

    @Override public LinkedList<Node> visitSatisfy_cond(XQueryParser.Satisfy_condContext ctx) {
        LinkedList<Node> results = new LinkedList<>();
        if (satisfy_helper(0, ctx)){
            Node n = doc.createTextNode("true");
            results.add(n);
        }
        return results;
    }

    @Override public LinkedList<Node> visitBrace_cond(XQueryParser.Brace_condContext ctx) {
        return visit(ctx.cond());
    }

    @Override public LinkedList<Node> visitOr_cond(XQueryParser.Or_condContext ctx) {
        LinkedList<Node> left = new LinkedList<>(visit(ctx.cond(0)));
        LinkedList<Node> right = new LinkedList<>(visit(ctx.cond(1)));
        LinkedList<Node> results = new LinkedList<>();

        if (left.size() > 0 || right.size() > 0){
            Node n = doc.createTextNode("true");
            results.add(n);
        }
        return results;

    }

    @Override public LinkedList<Node> visitNot_cond(XQueryParser.Not_condContext ctx) {
        LinkedList<Node> oppResult = new LinkedList<>(visit(ctx.cond()));
        LinkedList<Node> results = new LinkedList<>();
        if (oppResult.isEmpty()){
            Node n = doc.createTextNode("true");
            results.add(n);
        }
        return results;
    }

    @Override public LinkedList<Node> visitStartTag(XQueryParser.StartTagContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitEndTag(XQueryParser.EndTagContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitVar(XQueryParser.VarContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitSingle_ap(XQueryParser.Single_apContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitDouble_ap(XQueryParser.Double_apContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitDoc(XQueryParser.DocContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAtt_rp(XQueryParser.Att_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitFilter_rp(XQueryParser.Filter_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitParent_rp(XQueryParser.Parent_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitSelf_rp(XQueryParser.Self_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitBrace_rp(XQueryParser.Brace_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitTag_rp(XQueryParser.Tag_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitText_rp(XQueryParser.Text_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitComma_rp(XQueryParser.Comma_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitSingle_rp(XQueryParser.Single_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitChildren_rp(XQueryParser.Children_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitDouble_rp(XQueryParser.Double_rpContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitRp_flt(XQueryParser.Rp_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitBrace_flt(XQueryParser.Brace_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitNot_flt(XQueryParser.Not_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitEq_flt(XQueryParser.Eq_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitIs_flt(XQueryParser.Is_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAnd_flt(XQueryParser.And_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitOr_flt(XQueryParser.Or_fltContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitTagName(XQueryParser.TagNameContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAttName(XQueryParser.AttNameContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitFilename(XQueryParser.FilenameContext ctx) { return visitChildren(ctx); }
}
