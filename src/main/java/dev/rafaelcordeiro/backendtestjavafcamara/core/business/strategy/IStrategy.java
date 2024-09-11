package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCaseResult;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import org.slf4j.Logger;

/**
 * <h1><code>IStrategy</code></h1>
 *
 * Interface para a implementação do Design Pattern Strategy.
 *
 * @param <E> entidade que extende {@link DomainEntity}
 *
 * @author Rafael Cordeiro
 */
public interface IStrategy<E extends DomainEntity> {
    /**
     * <h1><code>execute()</code></h1>
     *
     * Procedimento implementado da strategy
     * @param entity Entidade a ser manipulada
     * @param businessCase Objeto relativo ao Business Case executado
     *
     * @author Rafael Cordeiro
     */
    void execute(E entity, BusinessCase<E> businessCase);

    /**
     * <h1><code>handleException()</code></h1>
     *
     * <p>Método padrão para tratamento de exceptions nas strategies, logando a mensagem da exception e
     * abortando execução da pipeline</p>
     * @param log Classe {@link Logger} da strategy implementada
     * @param e Exception disparada
     * @param businessCase Objeto relativo ao Business Case executado
     *
     * @author Rafael Cordeiro
     */
    default void handleException(Logger log, Exception e, BusinessCase businessCase) {
        log.error(e.getMessage(), e);
        businessCase.abortExecution(e.getMessage());
    }
}
