package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCasesExecutor;
import dev.rafaelcordeiro.backendtestjavafcamara.core.util.InvalidStrategyConditionException;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CRUDFacade<E extends DomainEntity> implements ICRUDFacade<E> {

    @Autowired
    BusinessCasesExecutor executor;

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

    @Override
    public void delete(E entity, String caseName) {
        BusinessCase<E> businessCase = new BusinessCase<E>().builder()
                .withName(caseName)
                .build();
        executor.run(entity, businessCase);

        if (!businessCase.getResult().getSuccess())
            throw new InvalidStrategyConditionException(businessCase.getResult().getPlainMessages());

    }

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
