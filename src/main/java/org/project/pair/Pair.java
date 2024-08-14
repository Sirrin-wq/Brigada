package org.project.pair;

public class Pair <K, V> {
    /*
    1. Создать класс Pair (пара), обобщенный двумя параметрами типа K и V:
    - имеет два поля типов K и V
    - имеет конструктор, принимающий 2 параметра типов K и V
    - имеет getters и setters для обоих полей
     */
    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    /*
    2. Добавить методы:
    - обобщенный getSwapped(), который возвращает текущую Pair, где элементы пары поменяли местами
    - статический обобщенный swap(), принимающий параметр Pair и возвращающий Pair, где значения в паре поменяли местами
     */
    public Pair<V,K> getSwapped(){
        return new Pair<>(value, key);
    }

    public static <V,K> Pair<V,K> swap(Pair<K,V> pair){
        return new Pair<>(pair.getValue(), pair.getKey());
    }
}
