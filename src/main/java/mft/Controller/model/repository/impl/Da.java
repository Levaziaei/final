package mft.Controller.model.repository.impl;

public interface Da<T> {
    T save(T t) throws Exception;
    T edit(T t) throws Exception;
    T remove(T t) throws Exception;

}
