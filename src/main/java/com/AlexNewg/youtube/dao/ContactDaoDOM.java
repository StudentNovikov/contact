package com.AlexNewg.youtube.dao;

import com.AlexNewg.youtube.model.Contact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ContactDaoDOM {
    private List<Contact> allContacts = new ArrayList<>();

    public List<Contact> getAllContacts() {
        allContacts.clear();
        initData();
        return allContacts;
    }

    public void initData() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("contacts.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();

            XPathExpression expressionName = xPath.compile("//contact/name//text()");
            XPathExpression expressionDescriptions = xPath.compile("//contact/description//text()");
            XPathExpression expressionGroupIDs = xPath.compile("//contact/groupId//text()");

            NodeList nameNodes = (NodeList) expressionName.evaluate(doc, XPathConstants.NODESET);
            NodeList descriptionNodes = (NodeList) expressionDescriptions.evaluate(doc, XPathConstants.NODESET);
            NodeList groupsNodes = (NodeList) expressionGroupIDs.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nameNodes.getLength(); i++) {
                String name = nameNodes.item(i).getNodeValue();
                String description = descriptionNodes.item(i).getNodeValue();
                String groupsIds = groupsNodes.item(i).getNodeValue();
                Element p = (Element) nameNodes.item(i).getParentNode().getParentNode();
                int id = Integer.parseInt(p.getAttribute("id"));
                allContacts.add(new Contact(id, name, description, groupsIds));
            }

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            System.out.println(e.getMessage());
        }

    }
}
