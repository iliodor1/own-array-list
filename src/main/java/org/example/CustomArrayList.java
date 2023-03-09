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
public class CustomArrayList<E> {
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
    public void clear() {
        for (Object element : array) {
            element = null;
        }
        size = 0;
    }

    /**
     * Получает размер массива.
     *
     * @return возвращает размер массива.
     */

    public int size() {
        return size;
    }

    /**
     * Меняет позицию заданного элемента в списке по индексу
     *
     * @param index   индекс новой позиции элемента в списке
     * @param element элемент, который нужно переместить на новую позицию в списке
     */
    private void set(int index, E element) {
        Objects.checkIndex(index, size);
        array[index] = element;
    }

    /**
     * Метод сортирует объекты в натуральном порядке. Список должен содержать объекты реализующие интерфейс Comparable.
     */
    public static <E extends Comparable<E>> void sort(CustomArrayList<E> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static <E extends Comparable<E>> void quickSort(CustomArrayList<E> list, int left, int right) {
        if (left < right) {
            int pivotIndex = separate(list, left, right);
            quickSort(list, left, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, right);
        }
    }

    private static <E extends Comparable<E>> int separate(CustomArrayList<E> list, int left, int right) {
        E pivot = list.get(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, right);
        return i + 1;
    }

    private static <E> void swap(CustomArrayList<E> list, int i, int j) {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Метод сортирует объекты в соответствии с порядком, заданным компаратором(Comparator).
     *
     * @param comparator - компаратор для определения порядка объектов.
     */
    public static <T> void sort(CustomArrayList<T> list, Comparator<T> comparator) {
        quickSort(list, comparator, 0, list.size() - 1);
    }

    public static <T> void quickSort(CustomArrayList<T> list, Comparator<T> comparator, int left, int right) {
        if (left < right) {
            int pivotIndex = separate(list, comparator, left, right);
            quickSort(list, comparator, left, pivotIndex - 1);
            quickSort(list, comparator, pivotIndex + 1, right);
        }
    }

    private static <T> int separate(CustomArrayList<T> list, Comparator<T> comparator, int left, int right) {
        T pivot = list.get(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, right);
        return i + 1;
    }
}
