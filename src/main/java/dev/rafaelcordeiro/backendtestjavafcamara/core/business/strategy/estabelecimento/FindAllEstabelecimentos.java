package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.estabelecimento;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.EstabelecimentoJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <h1><code>FindAllEstabelecimentos</code></h1>
 *
 * Strategy que consulta e retorna lista de {@link Estabelecimento}.
 *
 * @author Rafael Cordeiro
 */
@Component
@Slf4j
public class FindAllEstabelecimentos implements IStrategy<Estabelecimento> {

    @Autowired
    EstabelecimentoJPARepository repository;

    @Override
    public void execute(Estabelecimento entity, BusinessCase<Estabelecimento> businessCase) {
        try {
            businessCase.getResult().setEntities(Optional.of(repository.findAll()));
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}
