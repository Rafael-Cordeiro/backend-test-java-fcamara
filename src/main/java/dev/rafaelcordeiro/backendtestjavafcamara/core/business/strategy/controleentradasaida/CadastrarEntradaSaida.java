package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.ControleEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.ControleEntradaSaidaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
public class CadastrarEntradaSaida implements IStrategy<ControleEntradaSaida> {

    @Autowired
    ControleEntradaSaidaRepository repository;

    @Override
    public void execute(ControleEntradaSaida entity, BusinessCase<ControleEntradaSaida> businessCase) {
        try {
            if (businessCase.getResult().getSuccess()) {
                entity.setRegistro(Instant.now());
                businessCase.getResult().setEntity(Optional.of(repository.save(entity)));
            } else {
                log.error("Status de erro na execução das validações. Abortando cadastro de entrada ou saída");
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}