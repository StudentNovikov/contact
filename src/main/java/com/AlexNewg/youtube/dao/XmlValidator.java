package com.AlexNewg.youtube.dao;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    public boolean validateGroupXml() {
        File schemaFile = new File("groupXsd.xsd"); // etc.
        Source xmlFile = new StreamSource(new File("groups.xml"));
        return validate(schemaFile, xmlFile);
    }

    public boolean validateContactXml() {
        File schemaFile = new File("contactXsd.xsd"); // etc.
        Source xmlFile = new StreamSource(new File("contacts.xml"));
        return validate(schemaFile, xmlFile);
    }

    private boolean validate(File schemaFile, Source xmlFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            return true;
        } catch (SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
