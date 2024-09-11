package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;

import java.util.List;

/**
 * <h1><code>ICRUDFacade</code></h1>
 *
 * <p>Interface para o design pattern Facade, que disponibiliza métodos padrão para a criação de CRUD. Esta interface
 * presume o uso de Business Cases em suas implementações.</p>
 *
 * @author Rafael Cordeiro
 */
public interface ICRUDFacade<E extends DomainEntity> {
    E persist(E entity, String caseName);
    void delete(E entity, String caseName);
    List<E> findAll(E entity, String caseName);
    E findById(Long id, String caseName);
}
