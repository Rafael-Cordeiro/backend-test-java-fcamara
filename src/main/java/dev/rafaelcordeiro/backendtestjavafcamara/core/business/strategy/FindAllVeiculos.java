package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.VeiculoJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class FindAllVeiculos implements IStrategy<Veiculo> {

    @Autowired
    VeiculoJPARepository repository;

    @Override
    public void execute(Veiculo entity, BusinessCase<Veiculo> businessCase) {
        try {
            businessCase.getResult().setEntities(Optional.of(repository.findAll()));
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}