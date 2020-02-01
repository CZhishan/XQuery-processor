import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.LinkedList;
import java.util.HashSet;



public class myXPathVisitor extends XPathBaseVisitor<LinkedList> {

    private LinkedList<Node> curNodes = new LinkedList<>();
    Document xmldoc = null;

    public LinkedList<Node> getChildren(LinkedList<Node> node) {
        LinkedList<Node> children = new LinkedList<>();
        for (Node n : node) {
            for (int i = 0; i < n.getChildNodes().getLength(); i++) {
                children.add(n.getChildNodes().item(i));
            }
        }
        return children;
    }

    public LinkedList<Node> getParents(LinkedList<Node> node) {
        LinkedList<Node> parents = new LinkedList<>();
        for (Node n: node) {
            Node p = n.getParentNode();
            if (!parents.contains(p)) {
                parents.add(p);
            }
        }
        return parents;
    }

    public LinkedList<Node> getDescendants(LinkedList<Node> node) {
        LinkedList<Node> descendts = new LinkedList<>();
        for (Node n: node) {
            if (n.getChildNodes().getLength() != 0) {
                for (int i = 0; i < n.getChildNodes().getLength(); i++) {
                    descendts.addAll(findalldescendts(n.getChildNodes().item(i)));
                }
            }
        }
        return descendts;
    }

    public LinkedList<Node> findalldescendts(Node n) {
        LinkedList<Node> alldesc = new LinkedList<>();
        for (int i = 0; i < n.getChildNodes().getLength(); i++) {
            alldesc.addAll(findalldescendts(n.getChildNodes().item(i)));
        }
        alldesc.add(n);
        return alldesc;
    }

    @Override public LinkedList<Node> visitSingle_ap(XPathParser.Single_apContext ctx) {
        visit(ctx.doc());
        return visit(ctx.rp());
    }

    @Override public LinkedList<Node> visitDouble_ap(XPathParser.Double_apContext ctx) {
        visit(ctx.doc());
        LinkedList<Node> descendts = getDescendants(curNodes);
        this.curNodes.addAll(descendts);
        return visit(ctx.rp());
    }

    @Override public LinkedList<Node> visitDoc(XPathParser.DocContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        DOMparser xml = new DOMparser(ctx.filename().getText());
        this.xmldoc = xml.getDoc();

        nodes.add(this.xmldoc);
        this.curNodes = nodes;
        return nodes;
    }

    @Override public LinkedList<Node> visitAtt_rp(XPathParser.Att_rpContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        String attName = ctx.attName().getText();
        for (Node n : curNodes) {
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                String attValue = e.getAttribute(attName);
                if (!attValue.equals("")) {
                    nodes.add(n);
                    System.out.println("Attribute " + attName + " = " + attValue);
                }
            }
        }
        this.curNodes = nodes;
        return nodes;
    }

    @Override public LinkedList<Node> visitFilter_rp(XPathParser.Filter_rpContext ctx) {
        visit(ctx.rp());
        this.curNodes = visit(ctx.f());
        return curNodes;
    }

    @Override public LinkedList<Node> visitParent_rp(XPathParser.Parent_rpContext ctx) {
        this.curNodes = getParents(this.curNodes);
        return curNodes;
    }

    @Override public LinkedList<Node> visitSelf_rp(XPathParser.Self_rpContext ctx) {
        return curNodes;
    }

    @Override public LinkedList<Node> visitBrace_rp(XPathParser.Brace_rpContext ctx) {
        return visit(ctx.rp());
    }

    @Override public LinkedList<Node> visitTag_rp(XPathParser.Tag_rpContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Node> childnodes = getChildren(this.curNodes);

        for (Node c: childnodes) {
            if (c.getNodeType() == Node.ELEMENT_NODE && c.getNodeName().equals(ctx.getText())) {
                nodes.add(c);
            }
        }
        this.curNodes = nodes;
        return nodes;
    }

    @Override public LinkedList<Node> visitText_rp(XPathParser.Text_rpContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Node> childnodes = getChildren(this.curNodes);
        for (Node c: childnodes) {
            if (c.getNodeType() == Node.TEXT_NODE) {
                nodes.add(c);
            }
        }
        return nodes;
    }

    @Override public LinkedList<Node> visitComma_rp(XPathParser.Comma_rpContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Node> temp = this.curNodes;
        visit(ctx.rp(0));
        LinkedList<Node> left = this.curNodes;
        nodes.addAll(left);
        this.curNodes = temp;
        visit(ctx.rp(1));
        LinkedList<Node> right = this.curNodes;
        nodes.addAll(right);
        this.curNodes = nodes;
        return nodes;
    }

    @Override public LinkedList<Node> visitSingle_rp(XPathParser.Single_rpContext ctx) {
        visit(ctx.rp(0));
        return visit(ctx.rp(1));
    }

    @Override public LinkedList<Node> visitChildren_rp(XPathParser.Children_rpContext ctx) {
        this.curNodes = getChildren(this.curNodes);
        return this.curNodes;
    }

    @Override public LinkedList<Node> visitDouble_rp(XPathParser.Double_rpContext ctx) {
        visit(ctx.rp(0));
        LinkedList<Node> descendts = getDescendants(this.curNodes);
        this.curNodes.addAll(descendts);

        return visit(ctx.rp(1));
    }

    @Override public LinkedList<Node> visitRp_flt(XPathParser.Rp_fltContext ctx) {
        LinkedList<Node> temp = this.curNodes;
        LinkedList<Node> nodes = new LinkedList<>();
        for (Node n: temp){
            LinkedList<Node> newCurrent = new LinkedList<>();
            newCurrent.add(n);
            this.curNodes = newCurrent;
            if (visit(ctx.rp()).size() > 0)
                nodes.add(n);
        }
        this.curNodes = nodes;
        return nodes;
    }

    @Override public LinkedList<Node> visitBrace_flt(XPathParser.Brace_fltContext ctx) {

        return visit(ctx.f());
    }

    @Override public LinkedList<Node> visitNot_flt(XPathParser.Not_fltContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(this.curNodes);
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f()));
        HashSet<Node> difference = new HashSet<Node>();
        difference.addAll(leftSet);
        difference.removeAll(rightSet);

        LinkedList<Node> nodes = new LinkedList<Node>(difference);
        return nodes;
    }

    @Override public LinkedList<Node> visitEq_flt(XPathParser.Eq_fltContext ctx) {
        LinkedList<Node> temp = this.curNodes;
        LinkedList<Node> nodes = new LinkedList<>();

        for (Node n: temp){
            LinkedList<Node> newCurrent = new LinkedList<>();
            newCurrent.add(n);
            this.curNodes = newCurrent;

            LinkedList<Node> left = visit(ctx.rp(0));
            this.curNodes = newCurrent;
            LinkedList<Node> right = visit(ctx.rp(1));

            for (Node l: left)
                for (Node r: right)
                    if (l.isEqualNode(r) && !nodes.contains(n))
                        nodes.add(n);
        }
        this.curNodes = nodes;
        return nodes;
    }

    @Override public LinkedList<Node> visitIs_flt(XPathParser.Is_fltContext ctx) {
        LinkedList<Node> temp = this.curNodes;
        LinkedList<Node> nodes = new LinkedList<>();

        for (Node n : temp){
            LinkedList<Node> newCurrent = new LinkedList<>();
            newCurrent.add(n);
            this.curNodes = newCurrent;
            LinkedList<Node> left = visit(ctx.rp(0));
            this.curNodes = newCurrent;
            LinkedList<Node> right = visit(ctx.rp(1));

            for (Node l: left)
                for (Node r: right)
                    if (l.isSameNode(r) && !nodes.contains(n))
                        nodes.add(n);
        }
        this.curNodes = nodes;
        return nodes;

    }

    @Override public LinkedList<Node> visitAnd_flt(XPathParser.And_fltContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(visit(ctx.f(0)));
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f(1)));
        HashSet<Node> intersection = new HashSet<Node>();
        intersection.addAll(leftSet);
        intersection.retainAll(rightSet);

        LinkedList<Node> nodes = new LinkedList<Node>(intersection);
        return nodes;

    }

    @Override public LinkedList<Node> visitOr_flt(XPathParser.Or_fltContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(visit(ctx.f(0)));
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f(1)));
        HashSet<Node> union = new HashSet<Node>();
        union.addAll(leftSet);
        union.addAll(rightSet);
        LinkedList<Node> nodes = new LinkedList<Node>(union);
        return nodes;
    }

    @Override public LinkedList<Node> visitTagName(XPathParser.TagNameContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitAttName(XPathParser.AttNameContext ctx) { return visitChildren(ctx); }

    @Override public LinkedList<Node> visitFilename(XPathParser.FilenameContext ctx) { return visitChildren(ctx); }
}