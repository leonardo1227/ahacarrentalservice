package edu.mum.cs.cs425.ahacarrentalservice.service;


import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;

import java.util.List;

public interface IService<T> {
    public abstract List<T> findAll();
    public abstract List<T> findAll(String orderingProperty);
    public abstract T findById(Long id);
    public abstract T save(T t) throws ValidationException;
    public abstract void deleteById(Long id);
}
