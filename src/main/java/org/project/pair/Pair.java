package org.project.pair;

import java.util.Objects;

/**
 * A class representing a pair of two objects of any type.
 *
 * @param <K> the type of the first object
 * @param <V> the type of the second object
 */
public class Pair<K, V> {
    private K k;
    private V v;

    /**
     * Swaps the key and value of the given pair.
     *
     * @param pair the pair to swap
     * @return a new pair with the swapped key and value
     */
    public static <K, V> Pair<V, K> swap(Pair<K, V> pair) {
        return new Pair<>(pair.getV(), pair.getK());
    }

    /**
     * Constructs a new pair with the given key and value.
     *
     * @param k the key
     * @param v the value
     */
    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    /**
     * Returns a new pair with the swapped key and value.
     *
     * @return a new pair with the swapped key and value
     */
    public Pair<V, K> getSwapped() {
        return new Pair<>(this.getV(), this.getK());
    }

    /**
     * Compares this pair to another object for equality.
     *
     * @param o the object to compare with this pair
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        return Objects.equals(getK(), pair.getK()) && Objects.equals(getV(), pair.getV());
    }

    /**
     * Returns a hash code value for this pair.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(getK(), getV());
    }

    /**
     * Returns the key of this pair.
     *
     * @return the key
     */
    public K getK() {
        return k;
    }

    /**
     * Returns the value of this pair.
     *
     * @return the value
     */
    public V getV() {
        return v;
    }

    /**
     * Sets the key of this pair.
     *
     * @param k the new key
     */
    public void setK(K k) {
        this.k = k;
    }

    /**
     * Sets the value of this pair.
     *
     * @param v the new value
     */
    public void setV(V v) {
        this.v = v;
    }
}
