package com.petprojects.vending_machine_inventory.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, I> {
    Optional<T> getById(I id);

    List<T> getAll();

    T save(T entity);

    List<T> saveAll(List<T> entities);

    void delete(T entity);

    void deleteById(I id);
}
