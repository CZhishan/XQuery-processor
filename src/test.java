import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class test {
    public static void main(String[] args) {
        CharStream input = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                input = CharStreams.fromString(line);
            } finally {
                br.close();
            }
            //input = CharStreams.fromString("doc(\"xpathtest.xml\")//foods//text()");
            XPathLexer lexer = new XPathLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XPathParser parser = new XPathParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.ap();

            myXPathVisitor visitor = new myXPathVisitor();
            LinkedList<Node> results = visitor.visit(tree);
            Document outputDoc = null;
            try {
                DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
                DocumentBuilder docB = docBF.newDocumentBuilder();
                outputDoc = docB.newDocument();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            System.out.println(results.size());
            LinkedList<Node> finalResult = makeElem(visitor.xmldoc, "result", results);
            writeToFile(outputDoc, finalResult, "output/results.xml");


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }


    public static void writeToFile(Document doc, LinkedList<Node> result, String filePath) {
        Node newNode = doc.importNode(result.get(0), true);
        doc.appendChild(newNode);
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult res = new StreamResult(filePath);
            transformer.transform(source, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static LinkedList<Node> makeElem(Document doc, String tag, LinkedList<Node> list){
        Node result = doc.createElement(tag);
        for (Node node : list) {
            if (node != null) {
                Node newNode = doc.importNode(node, true);
                result.appendChild(newNode);
            }
        }
        LinkedList<Node> results = new LinkedList<>();
        results.add(result);
        return results;
    }
}
