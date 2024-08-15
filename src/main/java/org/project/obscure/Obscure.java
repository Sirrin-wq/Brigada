package org.project.obscure;

import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A class representing an {@link Obscure} object that may or may not be present.
 *
 * @param <T> the type of the {@link Obscure} object
 */
public class Obscure<T> {
  protected T object;

  /**
   * Creates a new instance of {@link Obscure} with the given object.
   *
   * @param object the object to be wrapped can be {@code null}
   * @param <T> the type of the {@link Obscure} object
   * @return a new instance of {@link Obscure} with the given object
   */
  @Contract(value = "_ -> new", pure = true)
  public static <T> @NotNull Obscure<T> of(T object) {
    return new Obscure<>(object);
  }

  /**
   * Creates a new instance of {@link Obscure} with a {@code null} object.
   *
   * @param <T> the type of the {@link Obscure} object
   * @return a new instance of {@link Obscure} with a {@code null} object
   */
  @Contract(value = " -> new", pure = true)
  public static <T> @NotNull Obscure<T> empty() {
    return new Obscure<>(null);
  }

  /**
   * Constructs a new instance of {@link Obscure} with the given object.
   *
   * @param object the object to be wrapped can be {@code null}
   */
  public Obscure(T object) {
    this.object = object;
  }

  /**
   * Checks if the wrapped object is present.
   *
   * @return {@code true} if the wrapped object isn't {@code null}, {@code false} otherwise
   */
  public T get() {
    return object;
  }

  /**
   * Checks if the wrapped object is present.
   *
   * @return {@code true} if the wrapped object isn't {@code null}, {@code false} otherwise
   */
  public boolean isPresent() {
    return object != null;
  }

  /**
   * Checks if the wrapped object is empty.
   *
   * @return {@code true} if the wrapped object is {@code null}, {@code false} otherwise
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
   * Returns the wrapped object if present, otherwise throws a runtime exception.
   *
   * <p>This approach allows you to dynamically pass the desired exception type and eliminates the
   * need to throw a generic exception. The code preceding uses a {@code Supplier} that returns a
   * specific exception.
   *
   * <p>If the wrapped object is present, this method returns the wrapped object. Otherwise, it
   * throws a runtime exception produced by the provided supplier.
   *
   * @param exceptionSupplier the supplier of the runtime exception to be thrown if the wrapped
   *     object is empty
   * @return the wrapped object if present
   * @throws RuntimeException the runtime exception produced by the provided supplier if the wrapped
   *     object is empty
   */
  public T orElseThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
    if (isPresent()) {
      return object;
    } else {
      throw exceptionSupplier.get();
    }
  }

  /**
   * Overrides the {@code equals} method to compare two {@code Obscure} objects for equality. Two
   * {@code Obscure} objects are considered equal if their wrapped objects are equal.
   *
   * @param o the object to be compared for equality with this {@code Obscure}
   * @return {@code true} if the specified object is equal to this {@code Obscure}; {@code false}
   *     otherwise
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
   * Overrides the {@code hashCode} method to return the {@code hashCode} of the wrapped object.
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
