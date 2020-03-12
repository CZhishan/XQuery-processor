import java.util.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.BufferedReader;
import java.io.FileReader;
import org.w3c.dom.Node;

public class testXquery {

    public static void main(String[] args) {
        CharStream input = null;
        try{
            String buffer = "";
            BufferedReader br = new BufferedReader(new FileReader("testjoin.txt"));
            String line = br.readLine();
            while (line != null) {
                buffer += line + ' ';
                line = br.readLine();
            }
            input = CharStreams.fromString(buffer);
            br.close();

            XQueryLexer lexer = new XQueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryParser parser = new XQueryParser(tokens);
            ParseTree tree = parser.xq();

            myXQueryVisitor visitor = new myXQueryVisitor();
            LinkedList<Node> results = visitor.visit(tree);
            LinkedList<Node> finalResult;
            if (results.size() == 1) {
                System.out.println(results.get(0).getChildNodes().getLength());
                finalResult = results;
            }
            else{
                System.out.println(results.size());
                finalResult = testXPath.makeElem(visitor.outputDoc, "result", results);
            }
            testXPath.writeToFile(visitor.outputDoc, finalResult, "output/joinresult.xml");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static LinkedList evalRewrited(String rewrited) {
        CharStream input = null;
        try {
            input = CharStreams.fromString(rewrited);
            XQueryLexer lexer = new XQueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryParser parser = new XQueryParser(tokens);
            ParseTree tree = parser.xq();

            myXQueryVisitor visitor = new myXQueryVisitor();
            visitor.reFlag = false;
            LinkedList<Node> results = visitor.visit(tree);
            return results;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
        return null;

    }
}

