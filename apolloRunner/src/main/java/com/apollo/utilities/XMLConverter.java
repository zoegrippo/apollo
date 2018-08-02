package com.apollo.utilities;

import org.springframework.stereotype.Component;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.*;

@Component
public class XMLConverter<T> {

    public String jaxbObjectToXML(T object) {
        try {
            JAXBContext contextObj = JAXBContext.newInstance(object.getClass());
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // to format XML

            StringWriter sw = new StringWriter();
            marshallerObj.marshal(object, sw);

            return sw.toString();
        }
        catch(JAXBException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public T jaxbXMLToObject(String xmlString, Class<T> paramClass) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(paramClass);
            Unmarshaller jaxbUnmarshaller =  jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            return (T) jaxbUnmarshaller.unmarshal(reader);
        } catch(JAXBException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}