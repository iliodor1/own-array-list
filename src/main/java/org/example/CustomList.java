package org.example;

import java.util.Comparator;

/**
 * Интерфейс представляет собой список объектов.
 * Интерфейс предоставляет методы для добавления объектов, получения объекта по индексу, удаление объекта по индексу, удаления всех объектов из списка и сортировки объектов.
 *
 * @param <E> тип хранимого объекта в списке.
 * @author Eldar Gainanov
 */
public interface CustomList<E> {
    /**
     * Метод добавляет объект в конец списка.
     *
     * @param element - объект, который нужно добавить в список.
     */
    void add(E element);

    /**
     * Метод добавляет объект в список по заданному индексу.
     * @param index - индекс добавляемого объекта.
     * @param element - объект, который нужно добавить в список.
     */
    void add(int index, E element);

    /**
     * Метод получает объект по индексу.
     *
     * @param index индекс возвращаемого объекта.
     * @return возвращает объект по индексу.
     * @throws IndexOutOfBoundsException если индекс объекта находится за пределами списка.
     */
    E get(int index) throws IndexOutOfBoundsException;

    /**
     * Метод удаляет объект из списка по индексу.
     *
     * @param index индекс удаляемого объекта из списка.
     * @throws IndexOutOfBoundsException если индекс объекта находиться за пределами списка.
     */
    void delete(int index) throws IndexOutOfBoundsException;

    /**
     * Метод удаляет все объекты в списке.
     */
    void clear();

    /**
     * Получает размер массива.
     * @return возвращает размер массива
     */
    int size();

    /**
     * Меняет позицию заданного элемента в списке по индексу
     * @param index   индекс новой позиции элемента в списке
     * @param element элемент, который нужно переместить на новую позицию в списке
     */
    void set(int index, E element);
}