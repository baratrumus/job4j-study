package links;

import java.io.IOException;
/*
С помощью Java сделать следующее:
        а) Считать файл со строчками вида
        A1;B1;C1
        A2;B2;C2
        б) Найти множество уникальных строчек и разбить его на непересекающиеся группы по следующему критерию:
        Если две строчки имеют совпадения непустых значений в одной или более колонках, они принадлежат одной группе.
        Например, строчки
        111;123;222
        200;123;100
        300;;100
        все принадлежат одной группе, так как первые две строчки имеют одинаковое значение 123 во второй колонке,
        а две последние одинаковое значение 100 в третьей колонке
        в) Вывести полученные группы в файл в следующем формате:
        Группа 1
        строчка1
        строчка2
        строчка3
        Группа 2
        строчка1
        строчка2
        строчка3
        В начале вывода указать получившиееся число групп с более чем одним элементом.
        Сверху расположить группы с наибольшим числом элементов.
        Архив с данными для выполнения задания lng.7z (https://github.com/PeacockTeam/new-job/blob/master/lng.7z)
        Приемлимое время работы на обыкновенном ноутбуке до 30 секунд.
        После выполнения задания необходимо отправить количество полученных групп.
*/

import java.io.*;
import java.util.*;

public class Links {
    List<TripleString> inputList = new LinkedList<>();
    List<ArrayList<TripleString>> groups = new ArrayList<>();
    ArrayList<TripleString> groupTmp = new ArrayList<>();
    ArrayList<Integer> deletedIndexes = new ArrayList<>();

    class TripleString {
        public String[] trip = new String[3];

        @Override
        public String toString() {
            return trip[0] + ";" + trip[1] + ";" + trip[2];
        }
    }

    private void garbageInfo() {
        if (inputList.size() == 720000) {
            System.gc();
            //System.out.println("МУсорщик пошел");
        }
        if ((inputList.size() < 900000) && (inputList.size() > 800000)) {
            System.out.println("меньше 900");
        } else if ((inputList.size() < 800000) && (inputList.size() > 770000)) {
            System.out.println("меньше 800");
        } else if ((inputList.size() < 710000) && (inputList.size() > 700000)) {
            System.out.println("меньше 710");
            System.out.println("МУсорщик пошел2");
            Runtime.getRuntime().gc();
        } else if ((inputList.size() < 700000) && (inputList.size() > 600000)) {
            System.out.println("меньше 700");
        } else if ((inputList.size() < 200000) && (inputList.size() > 100000)) {
            System.out.println("меньше 200");
        }
    }

    /**
     * за счет использования очереди как внешнего цикла и итератора как внутреннего
     * удается избежать Concurent Modification exception из-за удаления элементов,
     * которые мы забираем в группы, во внутреннем цикле
     */
    private void makeGroups() {
        Queue<TripleString> outerQueue = new LinkedList<>();
        outerQueue.offer(inputList.get(0));
        while (!outerQueue.isEmpty()) {
            TripleString ts = outerQueue.poll();
            groupTmp.add(ts);  //добавляем объект, с которым сравниваем остальные
            inputList.remove(ts);

            garbageInfo();

            ListIterator<TripleString> tsInnerIter = inputList.listIterator();
            while (tsInnerIter.hasNext()) {
                TripleString tsInner = tsInnerIter.next();
                for (int i = 2; i >= 0; i--) {  //сравниваем элементы строк
                    if (ts.trip[i].equals(tsInner.trip[i]) && !ts.trip[i].isEmpty()) {
                        groupTmp.add(tsInner);
                        tsInnerIter.remove();
                        break;
                    }
                }
            }
            ArrayList<TripleString> groupToAdd = new ArrayList<>(groupTmp);
            groups.add(groupToAdd); //завершаем эту группу
            groupTmp.clear();
            if (!inputList.isEmpty()) {
                outerQueue.offer(inputList.get(0));
            }

        }
    }


    /**
     * Набиваем связный список массивами по 3 строки.
     * Используем связный список, т.к. будем дальше из него часто удалять
     */
    private void writeToList(String str) {
        TripleString ts = new TripleString();
        //если кол-во ; в строке меньше 2 и массив из 3 эл по сплит не создается
        if (str.chars().filter(ch -> ch == ';').count() < 2) {
            ts.trip[0] = str;
            ts.trip[1] = "";
            ts.trip[2] = "";
        } else {
            ts.trip = str.split(";", 3);
        }
        inputList.add(ts);
    }

    private void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        String path = Links.class.getClassLoader().getResource("lng.csv").getFile();
            if (path.contains("%23")) {
                path = path.replace("%23", "#");
            }
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(line -> writeToList(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(int grCount) {
        String separator = File.separator;
        String tmpDirPath = System.getProperty("java.io.tmpdir") + separator + "LinksOUT";
        File tmpDir = new File(tmpDirPath);
        boolean created = tmpDir.mkdir();
        String filePath = tmpDirPath + separator + "target.csv";

        try (PrintWriter out = new PrintWriter(new FileOutputStream(filePath))) {
            out.println("Колличество групп с более чем одним элементом " + grCount);
            for (ArrayList<TripleString> grp : groups) {
                int number = groups.indexOf(grp) + 1;
                out.println("Группа" + number);
                for (TripleString ts : grp) {
                    out.println(ts.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int groupCount() {
        int ret = 0;
        for (ArrayList<TripleString> gr : groups) {
            if (gr.size() > 1) {
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Links links = new Links();
        links.load();
        System.out.println("Load finished");
        links.makeGroups();
        links.groups.sort(new Comparator<ArrayList<TripleString>>() {
            public int compare(ArrayList<TripleString> ar1, ArrayList<TripleString> ar2) {
                return  ar2.size() - ar1.size();
            }
        });
        int grCount = links.groupCount();
        System.out.println(String.format("Получилось %d групп с более чем одним элементом", grCount));
        links.writeToFile(grCount);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Время работы " + (double) (System.currentTimeMillis() - timeConsumedMillis));
    }



}
