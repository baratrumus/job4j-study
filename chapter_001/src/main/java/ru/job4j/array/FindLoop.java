package ru.job4j.array;

public class FindLoop {
    /**
     * rst = -1; // если элемента нет в массиве, то возвращаем -1.
     * @param data входящий массив
     * @param el искомый элемент
     * @return индекс элемента
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
