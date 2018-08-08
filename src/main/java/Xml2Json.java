import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Xml to Json
 */
public class Xml2Json {

    public static void main(String[] args) {
        String xmlPath = "/home/huh/gits/java_exam/Exam1/pom.xml";
        JsonObject json = new JsonObject();
        JsonObject innerJson = new JsonObject();
        Element rootNode = getRootNode(xmlPath);
        readXml(rootNode, innerJson);
        json.add(rootNode.getName(), innerJson);
        System.out.println(json);
    }


    /**
     * get the root node of the user offered xml file.
     *
     * @param xmlPath, xml file path.
     * @return root node.
     */
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

    /**
     * xml2josn
     *
     * @param rootNode, root node of the user offered xml.
     * @param jsonObject, target json object.
     */
    public static void readXml(Element rootNode, JsonObject jsonObject) {
        try {
            Iterator<Element> it = rootNode.elementIterator();
            while (it.hasNext()) {
                Element currentNode = it.next();
                if (currentNode.elements().size() == 0) {
                    jsonObject.addProperty(currentNode.getName(), currentNode.getTextTrim());
                    readXml(currentNode, jsonObject);
                } else {
                    List<Element> elements = currentNode.elements();
                    JsonArray ja = new JsonArray();
                    for (Element dependency : elements) {
                        List<Element> e = dependency.elements();
                        JsonObject jo = new JsonObject();
                        JsonObject jd = new JsonObject();
                        for (Element groupID : e) {
                            jo.addProperty(groupID.getName(), groupID.getTextTrim());
                        }
                        jd.add(dependency.getName(), jo);
                        ja.add(jd);
                    }
                    jsonObject.add(currentNode.getName(), ja);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
