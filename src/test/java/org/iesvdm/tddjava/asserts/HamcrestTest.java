package org.iesvdm.tddjava.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;

import org.junit.jupiter.api.Test;

public class HamcrestTest {

    @Test
    void whenBooleanIsTrue() {
        assertThat(1 == 1, is(equalTo(true)));
    }

    @Test
    void whenBooleanIsFalse() {
        boolean flag =false;
        assertThat(flag, is(equalTo(false)));
    }

    @Test
    void whenObjectIsNull() {
        Object nullObj = null;
        assertThat(nullObj, is(nullValue()));

    }

    @Test
    void whenObjectIsNotNull() {
        Object obj = new Object();

        assertThat(obj, is(not(equalTo(nullValue()))));

    }


    @Test
    void shouldBeEqual() {
        final Integer ACTUAL = 9;
        final Integer EXPECTED = 9;

        assertThat(ACTUAL, is(equalTo(EXPECTED)));

    }

    @Test
    void shouldReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertThat(ACTUAL, is(sameInstance(EXPECTED)));

    }

    @Test
    void shouldNotReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertThat(ACTUAL, is(not(sameInstance(new Object()))));

    }

    @Test
    void shouldContainSameIntegers() {

        final int[] ACTUAL = new int[]{2, 5, 7};
        final int[] EXPECTED = new int[]{2, 5, 7};

        assertThat(ACTUAL, is(equalTo(EXPECTED)));

    }

    @Test
    void shouldContainTwoElements() {
        Object first = new Object();
        Object second = new Object();

        List<Object> list = Arrays.asList(first, second);

        assertThat(list, hasSize(2));

    }

    @Test
    void shouldNotContainAnyElement() {

        List<Object> list = Arrays.asList();

        assertThat(list, hasSize(0));

    }

    @Test
    void shouldContainCorrectElementsInGivenOrder() {
        Object first = new Object();
        Object second = new Object();

        List<Object> list = Arrays.asList(first, second);

        assertThat(list, contains(first, second));

    }


    @Test
    void shouldContainCorrectElementsInAnyOrder() {
        Object first = new Object();
        Object second = new Object();

        List<Object> list = Arrays.asList(first, second);


        assertThat(list, containsInAnyOrder(second, first));

    }

    @Test
    void shouldContainCorrectElementOnce() {

        Object first = new Object();
        Object second = new Object();

        List<Object> list = Arrays.asList(first, second);

        assertThat(list, hasItem(first));
        //¿?

    }

    @Test
    void shouldNotContainIncorrectElement() {

        Object first = new Object();
        Object second = new Object();

        List<Object> list = Arrays.asList(first, second);

        assertThat(list, not(hasItem(new Object())));

    }

    @Test
    void shouldContainCorrectKey() {
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map, hasKey(KEY));
    }

    @Test
    void shouldNotContainCorrectKey() {
        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";


        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map, not(hasKey(INCORRECT_KEY)));
    }

    @Test
    void shouldContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map, hasEntry(KEY, VALUE));

    }

    @Test
    void shouldNotContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map, not(hasEntry(INCORRECT_KEY, VALUE)));

    }

}
