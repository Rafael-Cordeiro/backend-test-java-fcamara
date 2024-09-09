package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;

import java.util.List;

public interface ICRUDFacade<E extends DomainEntity> {
    public E create(E entity);
    public E update(E entity);
    public E delete(E entity);
    public List<E> findAll();
    public E findById(Long id);
}
