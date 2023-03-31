package org.iesvdm.tddjava.asserts;



import org.junit.jupiter.api.Test;
import org.testng.IObjectFactory2;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;



public class AssertTest {

    @Test
    void whenBooleanIsTrue() {
        assertTrue(1 == 1);
    }

    @Test
    void whenBooleanIsFalse() {
        boolean flag =false;
        assertFalse(flag);
    }

    @Test
    void whenObjectIsNull() {
        Object nullObj = null;

        assertNull(nullObj);

    }

    @Test
    void whenObjectIsNotNull() {
        Object obj = new Object();

        assertNotNull(obj);

    }


    @Test
    void shouldBeEqual() {
        final Integer ACTUAL = 9;
        final Integer EXPECTED = 9;

        assertEquals(EXPECTED, ACTUAL);

    }

    @Test
    void shouldReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertSame(EXPECTED, ACTUAL);

    }

    @Test
    void shouldNotReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertNotSame(EXPECTED, new Object());

    }

    final int[] ACTUAL = new int[]{2, 5, 7};
    final int[] EXPECTED = new int[]{2, 5, 7};

    @Test
    void shouldContainSameIntegers() {
        final int[] ACTUAL = new int[]{2, 5, 7};
        final int[] EXPECTED = new int[]{2, 5, 7};

        assertArrayEquals(EXPECTED, ACTUAL);

    }

    @Test
    void shouldContainTwoElements() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertEquals(list.size(), 2);

    }

    @Test
    void shouldNotContainAnyElement() {

        List list = Arrays.asList();

        assertEquals(list.size(), 0);

    }

    @Test
    void shouldContainCorrectElementsInGivenOrder() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertIterableEquals(list, Arrays.asList(first, second));
    }


    @Test
    void shouldContainCorrectElementsInAnyOrder() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        List list2 = Arrays.asList(second, first);


        assertTrue(list.size() == list2.size());
        assertTrue(list.containsAll(list2));
        assertTrue(list2.containsAll(list));

    }

    @Test
    void shouldContainCorrectElementOnce() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertTrue(list.indexOf(first) != -1
                && list.indexOf(first) == list.lastIndexOf(first));

    }

    @Test
    void shouldNotContainIncorrectElement() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertTrue( !list.contains(new Object()) );

    }

    @Test
    void shouldContainCorrectKey() {
        final String KEY = "key";
        final String VALUE = "value";

        final Map map = new HashMap<>();
        map.put(KEY, VALUE);

        assertTrue(map.containsKey(KEY));
    }

    @Test
    void shouldNotContainCorrectKey() {
        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";


        final Map map = new HashMap<>();
        map.put(KEY, VALUE);

        assertFalse(map.containsKey(INCORRECT_KEY));
    }

    @Test
    void shouldContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(KEY, VALUE);
        assertTrue(map.entrySet().contains(entry));

    }

    @Test
    void shouldNotContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(INCORRECT_KEY, VALUE);
        assertFalse(map.entrySet().contains(entry));

    }

}
