package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import org.slf4j.Logger;

public interface IStrategy<E extends DomainEntity> {
    void execute(E entity, BusinessCase<E> businessCase);
    default void handleException(Logger log, Exception e, BusinessCase businessCase) {
        log.error(e.getMessage(), e);
        businessCase.abortExecution(e.getMessage());
    }
}
