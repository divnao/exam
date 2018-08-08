import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * Read XML
 */
public class Test_XML {

    public static void main(String[] args) {
        String xmlPath = "/home/huh/gits/java_exam/Exam1/pom.xml";

        JsonObject json = new JsonObject();
        Element rootNode = getRootNode(xmlPath);
        readXml(rootNode, json);
        System.out.println(json);
    }


    public static Element getRootNode(String xmlPath) {
        try {
            File xmlFile = new File(xmlPath);
            SAXReader xmlReader = new SAXReader();
            Document doc = xmlReader.read(xmlFile);
            return doc.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readXml(Element rootNode, JsonObject jsonObject) {
        try {
            Iterator<Element> it = rootNode.elementIterator();
            while (it.hasNext()) {
                Element currentNode = it.next();
                if (currentNode.getName() != null) {
                    System.out.print(currentNode.getName());
                    System.out.println(currentNode.getTextTrim());
                    if (currentNode.nodeIterator().hasNext()) {
                        jsonObject.addProperty(currentNode.getName(), currentNode.getTextTrim());
                        readXml(currentNode, jsonObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
