import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SiteParser {
    private static final Logger LOGGER = LogManager.getLogger(RunParsing.class.getName());

    private final static String FILTER = "(?!\\W*javascript)(java)";

    private Config config = new Config();
    private static List<Vacancy> vacancies = new ArrayList<>();
    private StoreSQL storeSQL;
    private boolean continueParsing = true;
    private String sitePage;
    LocalDateTime lastParseDate;

    public SiteParser(Connection connection) {
        this.storeSQL = new StoreSQL(connection);
        this.sitePage = this.config.get("SitePage");
    }

    public void parsePage(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();

        Elements tableRaws = doc.getElementsByAttributeValue("class", "postslisttopic");

        for (int i = 0; i < tableRaws.size(); i++) {
            Element elem = tableRaws.get(i).child(0);
            String vacancyName = elem.text();
            if (!goodName(vacancyName)) {
                continue;
            }
            String vacancyUrl = elem.attr("href");
            Elements parentElems = elem.parent().parent().children();
            String vDate = parentElems.get(5).text();
            LocalDateTime vacancyDate = this.getDateFromString(vDate);

            if (vacancyDate.isBefore(lastParseDate)) {
                continueParsing = false;
                break;
            }

            Document vDescription = Jsoup.connect(vacancyUrl).get();

            Elements messages = vDescription.getElementsByAttributeValue("class", "msgBody");
            Element descMess = messages.get(1);
            String vacancyDesc = descMess.text();
            vacancies.add(new Vacancy(vacancyName, vacancyDesc, vacancyDate, vacancyUrl));
        }

        this.storeSQL.addListOfVacancies(vacancies);
        LOGGER.info(String.format("New vacancies: %s", vacancies.size()));
        this.vacancies.clear();
    }

    /**
     *  filter name with regular expression
      * @param name vacancy name
     * @return
     */
    private boolean goodName(String name) {
        boolean state = true;
        Pattern pattern = Pattern.compile(FILTER,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        state = matcher.find();
        return state;
    }

    private LocalDateTime getDateFromString(String date) {
        String time = date.substring(date.indexOf(",") + 2);
        int hour = Integer.parseInt(time.split(":")[0].trim());
        int min = Integer.parseInt(time.split(":")[1].trim());
        LocalTime localTime = LocalTime.of(hour, min);

        date = date.substring(0, date.indexOf(",")).trim();
        LocalDate localDate = LocalDate.now();

        if (date.contains("вчера")) {
            localDate.minusDays(1);
        } else if (!date.contains("сегодня") && !date.contains("вчера")) {
            int year = Integer.parseInt("20" + date.substring(date.length() - 2));
            String stringMonth = date.substring(2, 6).trim();
            int day = Integer.parseInt(date.substring(0, 2).trim());
            localDate = LocalDate.of(year, getMonthFromDate(stringMonth), day);
        }
        return LocalDateTime.of(localDate, localTime);
    }

    private Month getMonthFromDate(String stringMonth) {
        Month result = Month.JANUARY;
        if ("фев".equals(stringMonth)) {
            result = Month.FEBRUARY;
        } else if ("мар".equals(stringMonth)) {
            result = Month.MARCH;
        } else if ("апр".equals(stringMonth)) {
            result = Month.APRIL;
        } else if ("май".equals(stringMonth)) {
            result = Month.MAY;
        } else if ("июн".equals(stringMonth)) {
            result = Month.JUNE;
        } else if ("июл".equals(stringMonth)) {
            result = Month.JULY;
        } else if ("авг".equals(stringMonth)) {
            result = Month.AUGUST;
        } else if ("сен".equals(stringMonth)) {
            result = Month.SEPTEMBER;
        } else if ("окт".equals(stringMonth)) {
            result = Month.OCTOBER;
        } else if ("ноя".equals(stringMonth)) {
            result = Month.NOVEMBER;
        } else if ("дек".equals(stringMonth)) {
            result = Month.DECEMBER;
        }
        return result;
    }

    public void parsing() {
        this.lastParseDate = storeSQL.getLastParseDate();
        try {
            continueParsing = true;
            int page = 1;
            while (continueParsing) {
                LOGGER.info("Parsing page " + page + " begin");
                parsePage(this.sitePage + '/' + page++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
