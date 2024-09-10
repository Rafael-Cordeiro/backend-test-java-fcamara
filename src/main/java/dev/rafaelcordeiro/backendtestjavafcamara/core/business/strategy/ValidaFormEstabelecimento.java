package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidaFormEstabelecimento implements IStrategy<Estabelecimento> {

    @Override
    public void execute(Estabelecimento entity, BusinessCase<Estabelecimento> businessCase) {
        try {
            if (entity.getNome() == null || entity.getNome().isEmpty()) {
                businessCase.getResult().addMessage("Campo nome está vazio");
            }
            if (entity.getCnpj() == null || entity.getCnpj().isEmpty()) {
                businessCase.getResult().addMessage("Campo CNPJ está vazio");
            }
            if (entity.getEndereco() == null || entity.getEndereco().isEmpty()) {
                businessCase.getResult().addMessage("Campo endereço está vazio");
            }
            if (entity.getTelefone() == null || entity.getTelefone().isEmpty()) {
                businessCase.getResult().addMessage("Campo telefone está vazio");
            }
            if (entity.getQtdeVagasMotos() == null) {
                businessCase.getResult().addMessage("Campo referente a quantidade de vagas de moto está vazio");
            }
            if (entity.getQtdeVagasCarros() == null) {
                businessCase.getResult().addMessage("Campo referente a quantidade de vagas de carro está vazio");
            }

            if (!businessCase.getResult().getSuccess()) log.error("Houveram campos de estabelecimento sem preenchimento");
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}
