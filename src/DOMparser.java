import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;


public class DOMparser {

    private Document doc = null;

    public DOMparser(String inputFileName) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File inputFile = new File(inputFileName);
            if (dBuilder != null) {
                this.doc = dBuilder.parse(inputFile);
            }
            if (this.doc != null) {
                this.doc.getDocumentElement().normalize();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Document getDoc() {

        return doc;
    }
}