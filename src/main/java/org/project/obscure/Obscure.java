package org.project.obscure;

import java.util.Objects;

/**
 * A class representing an obscure object that may or may not be present.
 *
 * @param <T> the type of the obscure object
 */
public class Obscure<T> {
    protected T object;

    /**
     * Creates a new instance of Obscure with the given object.
     *
     * @param object the object to be wrapped can be null
     * @param <T>    the type of the obscure object
     * @return a new instance of Obscure with the given object
     */
    public static <T> Obscure<T> of(T object) {
        return new Obscure<>(object);
    }

    /**
     * Creates a new instance of Obscure with a null object.
     *
     * @param <T> the type of the obscure object
     * @return a new instance of Obscure with a null object
     */
    public static <T> Obscure<T> empty() {
        return new Obscure<>(null);
    }

    /**
     * Constructs a new instance of Obscure with the given object.
     *
     * @param object the object to be wrapped can be null
     */
    public Obscure(T object) {
        this.object = object;
    }

    /**
     * Checks if the wrapped object is present.
     *
     * @return {@code true} if the wrapped object isn't null, {@code false} otherwise
     */
    public T get() {
        return object;
    }

    /**
     * Checks if the wrapped object is present.
     *
     * @return true if the wrapped object isn't null, false otherwise
     */
    public boolean isPresent() {
        return object != null;
    }

    /**
     * Checks if the wrapped object is empty.
     *
     * @return true if the wrapped object is null, false otherwise
     */
    public boolean isEmpty() {
        return !isPresent();
    }

    /**
     * Returns the wrapped object if present, otherwise returns the default object.
     *
     * @param defaultObject the default object to be returned if the wrapped object is empty
     * @return the wrapped object if present, otherwise the default object
     */
    public T orElse(T defaultObject) {
        return isPresent() ? object : defaultObject;
    }

    /**
     * Returns the wrapped object if present, otherwise throws the given exception.
     *
     * @param exception the exception to be thrown if the wrapped object is empty
     * @return the wrapped object if present
     * @throws Exception the given exception if the wrapped object is empty
     */
    public T orElseThrow(Exception exception) throws Exception {
        if (isPresent()) {
            return object;
        } else {
            throw exception;
        }
    }

    /**
     * Overrides the {@code equals} method to compare two {@code Obscure} objects for equality.
     * Two {@code Obscure} objects are considered equal if their wrapped objects are equal.
     *
     * @param o the object to be compared for equality with this {@code Obscure}
     * @return {@code true} if the specified object is equal to this {@code Obscure}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Obscure<?> obscure = (Obscure<?>) o;

        return Objects.equals(getObject(), obscure.getObject());
    }

    /**
     * Overrides the {@code hashCode} method to return the hash code of the wrapped object.
     *
     * @return the hash code of the wrapped object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getObject());
    }

    /**
     * Returns the wrapped object.
     *
     * @return the wrapped object
     */
    public T getObject() {
        return object;
    }

    /**
     * Sets the wrapped object.
     *
     * @param object the object to be wrapped
     */
    public void setObject(T object) {
        this.object = object;
    }
}
