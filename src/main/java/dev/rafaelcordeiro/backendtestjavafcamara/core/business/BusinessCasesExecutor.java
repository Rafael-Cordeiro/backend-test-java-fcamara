package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessCasesExecutor {

    @Autowired
    StrategiesMap strategiesMap;

    public <E extends DomainEntity> void run(E entity, BusinessCase businessCase) {
        List<IStrategy> strategies = strategiesMap.getStrategies(businessCase.getName());

        if (strategies != null) {
            for (IStrategy s : strategies) {
                s.execute(entity, businessCase);
                if (businessCase.getResult().getAbort())
                    break;
            }
        }
    }
}
