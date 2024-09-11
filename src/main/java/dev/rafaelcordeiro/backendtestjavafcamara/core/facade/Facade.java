package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCasesExecutor;
import dev.rafaelcordeiro.backendtestjavafcamara.core.util.InvalidStrategyConditionException;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <h1><code>Facade</code></h1>
 *
 * <p>Fachada para os métodos de CRUD, implementando a chamada da execução de business cases, bem como o tratamento do
 * resultado, podendo disparar {@link InvalidStrategyConditionException} em caso de erro.</p>
 *
 * @param <E> Entidade que extende {@link DomainEntity}
 * @author Rafael Cordeiro
 */
@Service
public class Facade<E extends DomainEntity> implements ICRUDFacade<E> {

    @Autowired
    BusinessCasesExecutor executor;

    /**
     * <h1><code>persist()</code></h1>
     *
     * Persiste entidade (Cadastra e Atualiza).
     *
     * @param entity Entidade a ser persistida
     * @param caseName Nome do Business Case
     * @throws InvalidStrategyConditionException Quando houver erro da execução do business case
     * @return Entidade persistida vinda da base de dados
     * @author Rafael Cordeiro
     */
    @Override
    public E persist(E entity, String caseName) {
        BusinessCase<E> businessCase = new BusinessCase<E>().builder()
                .withName(caseName)
                .build();
        executor.run(entity, businessCase);

        if (!businessCase.getResult().getSuccess())
            throw new InvalidStrategyConditionException(businessCase.getResult().getPlainMessages());

        Optional<E> optionalPersistedEntity = businessCase.getResult().getEntity();
        return optionalPersistedEntity.orElse(null);
    }

    /**
     * <h1><code>delete()</code></h1>
     *
     * Deleta entidade.
     *
     * @param entity Entidade a ser deletada
     * @param caseName Nome do Business Case
     * @throws InvalidStrategyConditionException Quando houver erro da execução do business case
     * @author Rafael Cordeiro
     */
    @Override
    public void delete(E entity, String caseName) {
        BusinessCase<E> businessCase = new BusinessCase<E>().builder()
                .withName(caseName)
                .build();
        executor.run(entity, businessCase);

        if (!businessCase.getResult().getSuccess())
            throw new InvalidStrategyConditionException(businessCase.getResult().getPlainMessages());

    }

    /**
     * <h1><code>findAll()</code></h1>
     *
     * Consulta lista de entidades.
     *
     * @param entity Entidade a ser consultada
     * @param caseName Nome do Business Case
     * @throws InvalidStrategyConditionException Quando houver erro da execução do business case
     * @return Lista de entidades consultadas
     * @author Rafael Cordeiro
     */
    @Override
    public List<E> findAll(E entity, String caseName) {
        BusinessCase<E> businessCase = new BusinessCase<E>().builder()
                .withName(caseName)
                .build();
        executor.run(entity, businessCase);

        if (!businessCase.getResult().getSuccess())
            throw new InvalidStrategyConditionException(businessCase.getResult().getPlainMessages());

        Optional<List<E>> optionalPersistedEntity = businessCase.getResult().getEntities();
        return optionalPersistedEntity.orElse(null);
    }

    @Override
    public E findById(Long id, String caseName) {
        return null;
    }
}
