package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidaFormVeiculo implements IStrategy<Veiculo> {

    @Override
    public void execute(Veiculo entity, BusinessCase<Veiculo> businessCase) {
        try {
            if (entity.getMarca() == null || entity.getMarca().isEmpty()) {
                businessCase.getResult().addMessage("Campo marca está vazio");
            }
            if (entity.getModelo() == null || entity.getModelo().isEmpty()) {
                businessCase.getResult().addMessage("Campo modelo está vazio");
            }
            if (entity.getCor() == null || entity.getCor().isEmpty()) {
                businessCase.getResult().addMessage("Campo cor está vazio");
            }
            if (entity.getPlaca() == null || entity.getPlaca().isEmpty()) {
                businessCase.getResult().addMessage("Campo placa está vazio");
            }
            if (entity.getTipo() == null || entity.getTipo().isEmpty()) {
                businessCase.getResult().addMessage("Campo tipo está vazio");
            }

            if (!businessCase.getResult().getSuccess()) log.error("Houveram campos de estabelecimento sem preenchimento");
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}