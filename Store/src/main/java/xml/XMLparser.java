package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLparser {
    public Map<String, String> parse() {
        Map<String, String> map = new LinkedHashMap<>();
        File file = new File("config.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Parsing Error " + e.toString());
            return null;
        }
        Node rootNode = doc.getFirstChild();

        NodeList rootChilds = rootNode.getChildNodes();
        String mainName = null;

        for (int i = 0; i < rootChilds.getLength(); i++) {
            if (rootChilds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                mainName = rootChilds.item(i).getTextContent();

                String key = rootChilds.item(i).getNodeName();
                String value = rootChilds.item(i).getTextContent();
                map.put(key, value);
            }
        }
        return map;
    }
}

