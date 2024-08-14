package org.project.obscure;

// Создать обобщенный класс Obscure. Этот класс фактически
// является оберткой над объектом и помогает работать с NullPointerException.
public class Obscure <T>{
    // 1
    // Добавить field, которое хранит в себе объект обобщенного типа.
    private T field;

    // 2
    // Добавить конструктор, который принимает объект обобщенного типа.
    public Obscure(T field){
        this.field = field;
    }

    // 3
    // Добавить метод get() (не путать с getter для field), который возвращает объект обобщенного типа.
    public T get(){
        return field;
    }

    // 4
    // Добавить метод isPresent(), который возвращает ответ типа Boolean, означающий существует ли объект обобщенного типа.
    public boolean isPresent(){
        return field != null;
    }

    // 5
    // Добавить метод isEmpty(), который возвращает ответ типа Boolean, означающий пустой ли объект обобщенного типа.
    public boolean isEmpty(){
        return field == null;
    }

    // 6
    // Добавить метод orElse(), который принимает объект обобщенного типа (далее default object).
    // Метод возвращает объект обобщенного типа, который хранится в Obscure.
    // Если его не существует, метод возвращает default object.
    public T orElse(T object){
        return isPresent() ? field : object;
    }

    // 7
    // Добавить метод orElseThrow(), который принимает исключение.
    // Если объект обобщенного типа существует, то он возвращает.
    // Если объект обобщенного типа не существует, то он выбрасывает исключение, которое передано в метод.
    public <X extends Exception> T orElseThrow(X exception) throws X {
        if (isPresent()){
            return field;
        } else {
            throw exception;
        }
    }

    // 8
    // Добавить статический метод of(), который возвращает объект Obscure обобщенного типа.
    // Этот метод принимает объект обобщенного типа.
    public static <T> Obscure<T> of(T object){
        return new Obscure<>(object);
    }

    // 9
    // Добавить статический метод empty(), который возвращает пустой объект Obscure обобщенного типа.
    public static <T> Obscure<T> empty(){
        return new Obscure<>(null);
    }
}
