package tdd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 *
1. Генератор должен получать входную строку с ключами в тексте и список значений по этим ключам.
Например. Входящая строка String template = "I am a ${name}, Who are ${subject}? " и список значений
ассоциированных по ключу name -> "Petr", subject -> "you"
На выходе должна быть строка - "I am Petr, Who are you?"
Другой пример. " Help, ${sos}, ${sos}, ${sos}", sos -> "Aaaa". Должно получится " Help, Ааа, Ааа, Ааа"
2. Программа должна учитывать. что ключей нет в карте и кидать исключение.
3. Программа должна учитываться. что в карте есть лишние ключи и тоже кидать исключение.
В качестве карты можно использовать Map<String, String>, либо Pair[] - Pair - объект из библиотеки guava.
В задаче нужно использовать RegExp
private final Pattern KEYS = Pattern.compile(...); это должно быть поле. В задаче нужно объяснить почему.
 */
public class SimpleGenerator {
    private final Pattern pattern = Pattern.compile("\\$\\{([a-zA-Z]+)\\}");

    public String generate(String template, Map<String, String> values) {
        Matcher matcher = pattern.matcher(template);
        long resultCount = matcher.results().map(k -> k.group(1)).distinct().count();
        if (resultCount != values.size()) {
            throw new RuntimeException("amount of keys  differs");
        }
        return matcher.replaceAll(result -> values.get(result.group(1)));
    }
}
