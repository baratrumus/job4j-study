package ru.job4j.xmljdbc;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestRun {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //создаем и наполняем базу
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(new Config());
        storeSQL.generate(50000);

        //создаем исходный xml и наполняем из базы через список
        File storeXmlFile = makeFile("StoreXmlFile.xml");
        StoreXml storeXML = new StoreXml(storeXmlFile);
        storeXML.save(storeSQL.load());

        //Трансформация xml через xslt шаблон
        //берем шаблон-схему из ресурсов
        ConvertXSQT convertXSQT = new ConvertXSQT();
        String xsltTemplatePath = TestRun.class.getClassLoader().getResource("template.xsl").getFile();
        if (xsltTemplatePath.contains("%23")) {
            xsltTemplatePath = xsltTemplatePath.replace("%23", "#");
        }
        File xsltTemplate = new File(xsltTemplatePath);

        File targetXML = makeFile("targetXML.xml");
        convertXSQT.convert(storeXmlFile, targetXML, xsltTemplate);

        //Парсер Xml файла. Приложение парсит выходной файл и выводит
        //сумму значений всех атрибутов field в консоль.
        ParserSaxCounter psc = new ParserSaxCounter(targetXML);
        Long sum = psc.countSum();
        System.out.println(sum);
    }

    private static File makeFile(String filename) throws IOException {
        String separator = File.separator;
        String tmpDirPath = System.getProperty("java.io.tmpdir") + separator + "XmlJdbc";
        File tmpDir = new File(tmpDirPath);
        boolean created = tmpDir.mkdir();
        String filePath = tmpDirPath + separator + filename;
        File retFile = new File(filePath);
        if (retFile.exists()) {
            retFile.delete();
        }
        retFile.createNewFile();
        return retFile;
    }
}
