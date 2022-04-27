package by.issoft.domain.xmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class XmlParser {

    public static HashMap<String, String> getProductSorting() {

        HashMap<String, String> sortMap = new LinkedHashMap<>();

        File configFile = new File("onlineStore/domain/src/main/resources/config.xml");

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            // read from resources folder
            Document document = documentBuilder.parse(configFile);

            NodeList sortingNodeList = document.getElementsByTagName("sort").item(0).getChildNodes();

            for (int i = 0; i > sortingNodeList.getLength(); i++) {

                if (sortingNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                    sortMap.put(sortingNodeList.item(i).getNodeName(), sortingNodeList.item(i).getTextContent());

                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return sortMap;
    }
}

