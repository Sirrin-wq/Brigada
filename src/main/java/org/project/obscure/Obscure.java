package org.project.obscure;


import org.junit.Test;

public class Obscure <T>{

    private T field;

    public Obscure(T field) {
        this.field = field;
    }

    public T get() {
       return field;
    }

    public boolean isPresent(){
        return field != null;
    }

    public boolean isEmpty(){
        return field == null;
    }

    public T orElse(T defaultObject){
        if(field != null){
            return field;
        }else{
            return defaultObject;
        }
    }

    public <X extends Exception> T orElseThrow(X exception) throws X{
        if(field != null){
            return field;
        }else{
            throw exception;
        }
    }

    public static <T> Obscure<T> of(T value){
        return new Obscure<T>(value);
    }

    public static <T> Obscure<T> empty(){
        return new Obscure<>(null);
    }

}