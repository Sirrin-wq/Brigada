package org.project.test.pair;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.pair.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PairTest {
    @DisplayName("This test tests initials of Pair classes and getters/setters")
    @Test
    public void testGettersAndSetters() {
        Pair<String, Integer> pair = new Pair<>("key", 123);

        //Testing initial values
        assertEquals("key", pair.getK());
        assertEquals(123, pair.getV());

        //Testing setters
        pair.setK("newKey");
        pair.setV(777);

        //Testing modified values
        assertEquals("newKey", pair.getK());
        assertEquals(777, pair.getV());

    }

    @DisplayName("This test tests getSwapped() method")
    @Test
    public void testGetSwapped() {
        Pair<String, Integer> pair = new Pair<>("key", 123);
        Pair<Integer, String> swappedPair = pair.getSwapped();

        System.out.println(swappedPair.getK() + " " + swappedPair.getV());
    }

    @DisplayName("This test tests swap() method")
    @Test
    public void testSwapped(){
        Pair<String, Integer> pair = new Pair<>("key", 123);
        Pair<Integer, String> swappedPair = Pair.swap(pair);

        assertEquals(123, swappedPair.getK());
        assertEquals("key", swappedPair.getV());

        System.out.println(swappedPair.getK() + " " + swappedPair.getV());
    }

}
