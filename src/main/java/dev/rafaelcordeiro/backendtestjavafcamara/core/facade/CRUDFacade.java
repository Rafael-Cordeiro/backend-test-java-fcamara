package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;

import java.util.List;

public class CRUDFacade<E extends DomainEntity> implements ICRUDFacade<E> {
    @Override
    public E create(E entity) {
        return null;
    }

    @Override
    public E update(E entity) {
        return null;
    }

    @Override
    public E delete(E entity) {
        return null;
    }

    @Override
    public List<E> findAll() {
        return List.of();
    }

    @Override
    public E findById(Long id) {
        return null;
    }
}
