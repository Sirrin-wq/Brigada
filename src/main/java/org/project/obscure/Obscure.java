package org.project.obscure;

/*
    Create a generic Obscure class. This class is actually a wrapper over the object
    and helps to work with NullPointerException.
 */
public class Obscure<T> {

    // 1
    // Add a field that stores a generic object.
    private T field;

    // 2
    // Add a constructor that takes a generic type object.
    public Obscure(T field) {
        this.field = field;
    }

    // 3
    // Add a get() method (not to be confused with the getter for field) that returns a generic type object.
    public T get() {
        return field;
    }

    // 4
    // Add an isPresent() method that returns a Boolean response indicating whether the generic object exists.
    public boolean isPresent() {
        return field != null;
    }

    // 5
    // Add an isEmpty() method that returns a Boolean response indicating whether the generic object is empty.
    public boolean isEmpty() {
        return field == null;
    }

    // 7
    // Add the orElse() method that takes a generic object (hereinafter referred to as default object).
    // The method returns the generic object that is stored in Obscure.
    // If it does not exist, the method returns the default object.
    public T orElse(T defaultObject) {
        if (field != null) {
            return field;
        } else {
            return defaultObject;
        }
    }

    // 8
    // Add a method orElseThrow() that takes an exception.
    // If the generic object exists, it returns.
    // If the generic object does not exist, it throws the exception that is passed to the method.
    public <X extends Exception> T orElseThrow(X exception) throws X {
        if (field != null) {
            return field;
        } else {
            throw exception;
        }
    }

    // 9
    // Add a static of() method that returns a generic Obscure object. This method accepts a generic object.
    public static <T> Obscure<T> of(T value) {
        return new Obscure<T>(value);
    }

    // 10
    // Add a static method empty() that returns an empty Obscure object of generic type.
    public static <T> Obscure<T> empty() {
        return new Obscure<>(null);
    }

}