package ru.job4j.xmljdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * Создаем модель описания данных JavaBean.
 */
public class JustTestXmlUsage {
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entries {
        private List<Entry> values;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getValues() {
            return values;
        }

        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entry {
        private int value;

        public Entry() {
        }

        public Entry(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entries(Arrays.asList(new Entry(1), new Entry(2))),
                System.out
        );
    }
}
