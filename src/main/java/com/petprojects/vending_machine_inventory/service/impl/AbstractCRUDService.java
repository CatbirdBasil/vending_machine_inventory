package com.petprojects.vending_machine_inventory.service.impl;

import com.petprojects.vending_machine_inventory.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public class AbstractCRUDService<T, I> implements CrudService<T, I> {
    private final JpaRepository<T, I> repository;

    public AbstractCRUDService(JpaRepository<T, I> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> getById(I id) {
        log.trace("getById: id = {}, for entity of type {}", id, getClass());

        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        log.trace("getAll for entity of type {}", getClass());

        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        log.trace("save: entity = {}, for entity of type {}", entity, getClass());

        return repository.save(entity);
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        log.trace("saveAll: entities = {}, for entity of type {}", entities, getClass());

        return repository.saveAll(entities);
    }

    @Override
    public void delete(T entity) {
        log.trace("delete: entity = {}, for entity of type {}", entity, getClass());

        repository.delete(entity);
    }

    @Override
    public void deleteById(I id) {
        log.trace("deleteById: id = {}, for entity of type {}", id, getClass());

        repository.deleteById(id);
    }
}
