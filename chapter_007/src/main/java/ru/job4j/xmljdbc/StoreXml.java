package ru.job4j.xmljdbc;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * Генерация XML из данных базы.
 */

public class StoreXml {
    private File target;

    public StoreXml(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        try {
            //создаем «контекст» чтобы от него создать маршаллер
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            //создаем объект Marshaller для сериализации
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //В результат будут добавлены переносы строки и пробелы, чтобы код был читабельным.
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            /*jaxbMarshaller.marshal(
                    new Entries(list.stream().map(e -> new Entry(e.getValue()))
                            .collect(Collectors.toList())), this.target);*/
            Entries entries = new Entries(list);
            jaxbMarshaller.marshal(entries, this.target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
