package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private CustomList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CustomArrayList<>(0);
    }

    @Test
    public void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSortInteger() {
        list.add(3);
        list.add(1);
        list.add(2);

        CustomArrayList.sort(list);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSortString() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Pavel");
        list.add("Eldar");
        list.add("Anna");

        CustomArrayList.sort(list);

        assertEquals("Anna", list.get(0));
        assertEquals("Eldar", list.get(1));
        assertEquals("Pavel", list.get(2));
    }

    @Test
    public void testSortByComparator() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Pavel");
        list.add("Eldar");
        list.add("Anna");

        CustomArrayList.sort(list, String::compareTo);

        assertEquals("Anna", list.get(0));
        assertEquals("Eldar", list.get(1));
        assertEquals("Pavel", list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        list.add(1);
        list.add(3);
        list.add(4);

        list.add(1, 2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    void testDelete() {
        list.add(1);
        list.add(3);
        list.add(4);

        list.delete(1);

        assertEquals(1, list.get(0));
        assertEquals(4, list.get(1));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(3);
        list.add(4);

        list.clear();

        assertEquals(0, list.size());
    }
}