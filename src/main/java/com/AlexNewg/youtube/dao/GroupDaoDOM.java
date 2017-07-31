package com.AlexNewg.youtube.dao;

import com.AlexNewg.youtube.model.Group;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GroupDaoDOM {

    private List<Group> allGroups = new ArrayList<>();


    public List<Group> getAllGroups() {
        allGroups.clear();
        initData();
        return allGroups;
    }

    public void initData() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("groups.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression expression = xPath.compile("//group/name//text()");

            NodeList nameNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nameNodes.getLength(); i++) {
                String name = nameNodes.item(i).getNodeValue();
                Element p = (Element) nameNodes.item(i).getParentNode().getParentNode();
                int id = Integer.parseInt(p.getAttribute("id"));
                allGroups.add(new Group(id, name));
            }

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            System.out.println(e.getMessage());
        }

    }

    public void update(String oldName, String newName) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse("groups.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression expression = xPath.compile("//group/name//text()");

            NodeList nameNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nameNodes.getLength(); i++) {
                String name = nameNodes.item(i).getNodeValue();
                if (name.equals(oldName)) {
                    nameNodes.item(i).setNodeValue(newName);
                    break;
                }
            }

            Transformer tr = TransformerFactory.newInstance().newTransformer();


            // send DOM to file
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream("groups.xml")));
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void create(String groupName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse("groups.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            //XPathExpression expression = xPath.compile("//group/name//text()");
            XPathExpression expression = xPath.compile("//group//text()");

            NodeList nameNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            //trying to insert
            Element g = doc.createElement("group");
            int maxId =  getMaxGroupId();
            g.setAttribute("id",Integer.toString(maxId));
            Text a = doc.createTextNode(groupName);
            Element p = doc.createElement("name");
            p.appendChild(a);
            g.appendChild(p);
           // nameNodes.item(0).getParentNode().getParentNode().insertBefore(g, nameNodes.item(3).getParentNode().getNextSibling());
            nameNodes.item(0).getParentNode().getParentNode().appendChild(g);

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            // send DOM to file
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream("groups.xml")));
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }



    private int getMaxGroupId() {
        initData();
        if (allGroups.isEmpty()) {
            return 1;
        }
        return allGroups.get(allGroups.size() - 1).getId() + 1;
    }


    public void delete(String groupName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse("groups.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression expression = xPath.compile("//group//text()");

            NodeList nameNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nameNodes.getLength(); i++) {
                String name = nameNodes.item(i).getNodeValue();
                if (name.equals(groupName)) {
                    nameNodes.item(i).getParentNode().getParentNode().getParentNode().removeChild(nameNodes.item(i).getParentNode().getParentNode());
                    break;
                }
            }

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            // send DOM to file
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream("groups.xml")));
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


}
