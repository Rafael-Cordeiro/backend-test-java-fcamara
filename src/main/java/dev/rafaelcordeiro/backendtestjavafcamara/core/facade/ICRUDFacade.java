package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;

import java.util.List;

public interface ICRUDFacade<E extends DomainEntity> {
    public E persist(E entity, String caseName);
    public void delete(E entity, String caseName);
    public List<E> findAll(E entity, String caseName);
    public E findById(Long id, String caseName);
}
