package com.AlexNewg.youtube.dao;

import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.Group;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.FileOutputStream;
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

    public void create(String contactName,String contactDescription, String contactGroups) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document doc = documentBuilder.parse("contacts.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression expression = xPath.compile("//contact//text()");

            NodeList nameNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            //trying to insert

            Text name = doc.createTextNode(contactName);
            Element nameElement = doc.createElement("name");
            Text description = doc.createTextNode(contactDescription);
            Element descriptionElement = doc.createElement("description");
            Text groups = doc.createTextNode(contactGroups);
            Element groupElement = doc.createElement("groupId");

            nameElement.appendChild(name);
            descriptionElement.appendChild(description);
            groupElement.appendChild(groups);

            Element resultContact = doc.createElement("contact");
            int maxId = getMaxContactId();
            resultContact.setAttribute("id", Integer.toString(maxId));

            resultContact.appendChild(nameElement);
            resultContact.appendChild(descriptionElement);
            resultContact.appendChild(groupElement);

            nameNodes.item(0).getParentNode().getParentNode().appendChild(resultContact);

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            // send DOM to file
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream("contacts.xml")));
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private int getMaxContactId() {
        initData();
        if (allContacts.isEmpty()) {
            return 1;
        }
        return allContacts.get(allContacts.size() - 1).getId() + 1;
    }


    public Contact readContact(String name) {
        for (Contact allContact : allContacts) {
            if (allContact.getName().equals(name)) {
                return allContact;
            }
        }
        return null;
    }

    public void update(String oldName, String newName) {
        for (Contact allContact : allContacts) {
            if (allContact.getName().equals(oldName)) {
                allContact.setName(newName);
                return;
            }
        }
    }

    public void updateContactDescription(String name, String description) {
        for (Contact allContact : allContacts) {
            if (allContact.getName().equals(name)) {
                allContact.setDescription(description);
                return;
            }
        }
    }

    public void delete(String name) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                allContacts.remove(i);
                return;
            }
        }
    }

//    public void updateContactGroup(String name, String group) {
//        for (Contact allContact : allContacts) {
//            if (allContact.getName().equals(name)) {
//                if (doesThisGroupExists(group) >= 0) {
//                    if (!isGroupAlreadyInContact(allContact, group)) {
//
//                        allContact.addGroup(allGroups.get(doesThisGroupExists(group)));
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    private int doesThisGroupExists(String group) {
//        for (int i = 0; i < allGroups.size(); i++) {
//            if (allGroups.get(i).getName().equals(group)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    private boolean isGroupAlreadyInContact(Contact contact, String group) {
        List<Group> groups = contact.getGroups();
        for (Group group1 : groups) {
            if (group1.getName().equals(group)) {
                return true;
            }
        }
        return false;
    }

    public void removeGroupFromContact(String name, String group) {
        for (Contact allContact : allContacts) {
            if (allContact.getName().equals(name)) {
                List<Group> groups = allContact.getGroups();
                for (int j = 0; j < groups.size(); j++) {
                    if (groups.get(j).getName().equals(group)) {
                        groups.remove(j);
                        return;
                    }
                }
            }
        }
    }

    public List<Contact> getAllMembersOfAGroup(String name) {
        List<Contact> contacts = new ArrayList<>();
        for (Contact allContact : allContacts) {
            List<Group> groups = allContact.getGroups();
            for (Group group : groups) {
                if (group.getName().equals(name)) {
                    contacts.add(allContact);
                }
            }
        }
        return contacts;
    }
}
