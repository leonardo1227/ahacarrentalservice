package edu.mum.cs.cs425.ahacarrentalservice.service;


import java.util.List;

public interface IOfferService<T> {
    public abstract List<T> findAll();
    public abstract List<T> findAll(String orderingProperty);
    public abstract T findById(Long id);
    public abstract T save(T t);
    public abstract void deleteById(Long id);
}
