package car_dealer.util.impl;

import car_dealer.util.XMLParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XMLParserImpl implements XMLParser {
    @Override
    @SuppressWarnings("unchecked")
    public <T> T fromXML(Class<T> klass, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(klass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> void toXML(T entity, String path) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(entity.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(entity, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
