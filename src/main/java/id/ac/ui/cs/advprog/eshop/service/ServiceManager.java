package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ServiceManager<T> {
    T create(T entity);
    List<T> findAll();
    T findById(String id);
    void deleteById(String id);
}
