package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.ControleEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.EntradaSaidaEnum;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.ControleEntradaSaidaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VerificarDisponibilidadeVaga implements IStrategy<ControleEntradaSaida> {

    @Autowired
    ControleEntradaSaidaRepository repository;

    @Override
    public void execute(ControleEntradaSaida entity, BusinessCase<ControleEntradaSaida> businessCase) {
        try {
            if (businessCase.getResult().getSuccess() && entity.getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA)) {
                var entradasSaidas = repository.findByEstabelecimento(entity.getEstabelecimento())
                        .stream().filter(it -> it.getVeiculo().getTipo().equals(entity.getVeiculo().getTipo())).toList();

                var vagasOcupadas = entradasSaidas.stream().filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA)).count() -
                        entradasSaidas.stream().filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.SAIDA)).count();

                if (entity.getVeiculo().getTipo().equals("A") && vagasOcupadas >= entity.getEstabelecimento().getQtdeVagasMotos()) {
                    businessCase.getResult().setSuccess(false);
                    businessCase.getResult().getMessages().add("O limite para vagas de moto do estabelecimento (ID " +
                            entity.getEstabelecimento().getId() + ") foi atingido. (Limite: " +
                            entity.getEstabelecimento().getQtdeVagasMotos() + ")");
                }
                if (entity.getVeiculo().getTipo().equals("B") && vagasOcupadas >= entity.getEstabelecimento().getQtdeVagasCarros()) {
                    businessCase.getResult().setSuccess(false);
                    businessCase.getResult().getMessages().add("O limite para vagas de carro do estabelecimento (ID " +
                            entity.getEstabelecimento().getId() + ") foi atingido. (Limite: " +
                            entity.getEstabelecimento().getQtdeVagasCarros() + ")");
                }
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}
