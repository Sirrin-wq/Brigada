package org.project.pair;

/*
1. Create a class Pair, generalized by two parameters of type K and V:
- has two fields of types K and V
- has a constructor that accepts 2 types of types K and V
- has getters and setters for both fields
 */

public class Pair<K, V> {
    private K k;
    private V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

/*
2. Add methods:
- generic getSwapped(), which returns the current Pair, where the elements of the pair have been swapped
- static generic swap(), which takes a Pair parameter and returns a Pair, where the values in the pair have been swapped
 */

    public Pair<V, K> getSwapped() {
        return new Pair<>(v, k);
    }

    public static <K, V> Pair<V, K> swap(Pair<K, V> pair) {
        return new Pair<>(pair.getV(), pair.getK());
    }

}
