
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * 1. Реализовать модуль сборки анализа Java вакансий с sql.ru, раздел работа
 * 2. Система должна использовать Jsoup для парсинга страниц.
 * 3. Система должна запускаться раз в день(библиотекa quartz) cron exression - 0 0 12 * * ?
 * Пример использования - http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-06.html
 * Для запуска раз в день нужно использовать
 * http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
 * 4. Система должна собирать данные только про вакансии java. учесть что JavaScript не подходит. как и Java Script.
 * 5. Данные должны храниться в базе данных.
 * В базе должна быть таблица create table vacancy (id, name, text, link)
 * id - первичный ключ
 * name - имя вакансии
 * text - текст ваканси
 * link - текст, ссылка на вакансию
 * 6. Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.
 * 7. Учитывать время последнего запуска. если это первый запуск. то нужно собрать все объявления с начало года.
 * 8. В системе не должно быть вывода, либо ввода информации. все настройки должны быть в файле. app.properties.
 * В файл app.properties указываем. настройки к базе данных и периодичность запуска приложения.
 * 9. для вывода нужной информации использовать логгер log4j. Описание здесь 4.1. Log4j 2. Логирование системы.
 * 10. Пример запуска приложения.
 * java -jar SqlRuParser app.properties
 */
public class RunParsing {

    private Config config = new Config();
    private String cronExpression = config.get("cron.time");
    private final static Logger LOGGER = LogManager.getLogger(StoreSQL.class);
    private static boolean propertiesFromArgs = false;


    public void sheduleParsing() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(JobExec.class).build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(String[] args) {
        RunParsing run = new RunParsing();
        if (run.propertiesFromArgs) {
            //get property file from args
            try {
                run.config = new Config(args[0]);
                run.cronExpression = run.config.get("cron.time");
                LOGGER.info("load properties from file...");
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                System.exit(1);
            }
        }

        //запуск по расписанию
        try {
            run.sheduleParsing();
        } catch (SchedulerException e) {
            LOGGER.error("ERROR", e);
        }

    }
}
