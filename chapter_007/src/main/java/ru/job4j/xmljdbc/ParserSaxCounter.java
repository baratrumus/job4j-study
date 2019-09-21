package ru.job4j.xmljdbc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Парсер Xml файла. Приложение парсит выходной файл и выводит
 * арифметическую сумму значений всех атрибутов field в консоль.
 */
public class ParserSaxCounter {
    private File file;

    public ParserSaxCounter(File file) {
        this.file = file;
    }

    public Long countSum()  throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(this.file, handler);
        return handler.getSum();
    }

    private static class XMLHandler extends DefaultHandler {
        private Long sum = 0L;

        public long getSum() {
            return sum;
        }

        /**
         * uri — это пространство, в котором находится элемент,
         * localName — это имя элемента с префиксом,
         * qName — это просто имя элемента.
         * uri и localName всегда пустые, если мы не подключили в фабрике обработку пространств
         * методом фабрики setNamespaceAware(true). Тогда мы сможем получать пространство (uri)
         * и элементы с префиксами перед ними (localName).
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("entry".equalsIgnoreCase(qName)) {
                String field = attributes.getValue("field");
                sum += Long.valueOf(field);
            }
        }
    }

}


