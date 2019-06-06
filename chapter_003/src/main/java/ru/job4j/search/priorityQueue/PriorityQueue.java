package ru.job4j.search.priorityQueue;

import java.util.LinkedList;
import java.util.ListIterator;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент, согласно приоритету.
     * Определим что существуют 5 уровней приоритета. 1 - наивысший
     * когда находим 1-й элемент приоритета выше чем добавляемый, ставим в эту позицию, т.е. перед ним
     * ListIterator может двигаться в обе стороны, в отличии от iterator.
     * И его указатель находится не на конкретном элементе, а всегда между элементами.
     * @param task задача
     */
    public void put(Task task) {
        ListIterator<Task> literator = this.tasks.listIterator();
        Task currentTask;
        boolean indexFound = false;
        int taskPriority = task.getPriority();
        while (literator.hasNext()) {
            currentTask = literator.next();
            if (currentTask.getPriority() > taskPriority) {
                literator.previous();
                literator.add(task);
                indexFound = true;
                break;
            }
        }
        if (!indexFound) {
            this.tasks.add(task);
        }

    }

    public Task take(int index) {
        return this.tasks.get(index);
    }
}