package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Это реализация динамического массива, которая использует массив для хранения элементов.
 *
 * @param <E> тип элемента, который будет храниться в списке.
 * @author Eldar Gainanov
 */
public class CustomArrayList<E> implements CustomList<E> {
    // емкость по умолчанию
    private static final int DEFAULT_CAPACITY = 10;
    // количество элементов в списке
    private int size;
    // массив элементов
    private Object[] array;

    /**
     * Конструктор без параметров создает пустой список с емкостью по умолчанию.
     */
    public CustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить в список.
     */
    @Override
    public void add(E element) {
        if (array.length == size) {
            increaseArray();
        } else {
            array[size++] = element;
        }
    }

    /**
     * Добавляет элемент в список по заданному индексу.
     *
     * @param index   индекс, по которому нужно добавить элемент.
     * @param element элемент, который нужно добавить в список.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == array.length) {
            increaseArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Увеличивает емкость списка в два раза.
     */
    private void increaseArray() {
        Object[] tempArray = array;
        var newLength = tempArray.length * 2;
        array = Arrays.copyOf(tempArray, newLength);
    }

    /**
     * Получает элемент из списка по индексу.
     *
     * @param index индекс возвращаемого объекта.
     * @return возвращает элемент по заданному индексу.
     */
    @Override
    public E get(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) array[index];
    }

    /**
     * Удаляет элемент по индексу
     *
     * @param index индекс удаляемого объекта из списка.
     */
    @Override
    public void delete(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    /**
     * Удаляет все элементы массива
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
    }

    /**
     * Получает размер массива.
     *
     * @return возвращает размер массива.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Меняет позицию заданного элемента в списке по индексу
     *
     * @param index   индекс новой позиции элемента в списке
     * @param element элемент, который нужно переместить на новую позицию в списке
     */
    @Override
    public void set(int index, E element) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        array[index] = element;
    }

    /**
     * Метод сортирует объекты в натуральном порядке. Список должен содержать объекты реализующие интерфейс Comparable.
     * @param list список элементов, который нужно отсортировать
     * @param <E> тип элемента в списке.
     */
    public static <E extends Comparable<E>> void sort(CustomList<E> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static <E extends Comparable<E>> void quickSort(CustomList<E> list, int left, int right) {
        if (right <= left) {
            return;
        }

        E pivot = list.get((left + right) / 2);
        int l = left;
        int r = right;
        while (l <= r) {
            while (list.get(l).compareTo(pivot) < 0) {
                l++;
            }
            while (list.get(r).compareTo(pivot) > 0) {
                r--;
            }
            if (l <= r) {
                swap(list, l, r);
                l++;
                r--;
            }
        }
        quickSort(list, left, l-1);
        quickSort(list, l, right);
    }

    private static <E> void swap(CustomList<E> list, int i, int j) {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Метод сортирует объекты в соответствии с порядком, заданным компаратором(Comparator).
     *
     * @param comparator - компаратор для определения порядка объектов.
     */
    public static <E> void sort(CustomList<E> list, Comparator<E> comparator) {
        quickSort(list, comparator, 0, list.size() - 1);
    }

    private static <E> void quickSort(CustomList<E> list, Comparator<E> comparator, int left, int right) {
        if (right <= left) {
            return;
        }

        E pivot = list.get((left + right) / 2);
        int l = left;
        int r = right;
        while (l <= r) {
            while (comparator.compare(list.get(l), pivot) < 0) {
                l++;
            }
            while (comparator.compare(list.get(r), pivot) > 0) {
                r--;
            }
            if (l <= r) {
                swap(list, l, r);
                l++;
                r--;
            }
        }
        quickSort(list, comparator, left, l-1);
        quickSort(list, comparator, l, right);
    }

}
