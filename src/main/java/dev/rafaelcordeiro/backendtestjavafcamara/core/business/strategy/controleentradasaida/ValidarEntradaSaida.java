package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.ControleEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.EntradaSaidaEnum;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.ControleEntradaSaidaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <h1><code>ValidarEntradaSaida</code></h1>
 *
 * <p>Strategy que valida se o veículo selecionado possui registro de entrada sem saída e valida se um
 * cadastro de entrada ou saída é válido em um estabelecimento especifico.</p>
 *
 * @author Rafael Cordeiro
 */
@Component
@Slf4j
public class ValidarEntradaSaida implements IStrategy<ControleEntradaSaida> {

    @Autowired
    ControleEntradaSaidaRepository repository;

    @Override
    public void execute(ControleEntradaSaida entity, BusinessCase<ControleEntradaSaida> businessCase) {
        try {
            List<ControleEntradaSaida> entradasSaidas = repository.findByVeiculoAndEstabelecimento(entity.getVeiculo(), entity.getEstabelecimento());
            if (!entradasSaidas.isEmpty()) {
                switch (entity.getEntradaSaida()) {
                    case EntradaSaidaEnum.ENTRADA:
                        if (Collections.max(entradasSaidas, Comparator.comparing(ControleEntradaSaida::getRegistro))
                                .getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA)) {
                            businessCase.getResult().setSuccess(false);
                            businessCase.getResult().getMessages()
                                    .add("Não pode ser cadastrada uma entrada do veículo de ID (" +
                                            entity.getVeiculo().getId() + ") no estabelecimento de ID (" +
                                            entity.getEstabelecimento().getId() + "), pois ainda consta uma entrada em aberto");
                        }
                        break;
                    case EntradaSaidaEnum.SAIDA:
                        if (Collections.max(entradasSaidas, Comparator.comparing(ControleEntradaSaida::getRegistro))
                                .getEntradaSaida().equals(EntradaSaidaEnum.SAIDA)) {
                            businessCase.getResult().setSuccess(false);
                            businessCase.getResult().getMessages().add("Não pode ser cadastrada uma saída do veículo de ID (" +
                                    entity.getVeiculo().getId() + ") no estabelecimento de ID (" +
                                    entity.getEstabelecimento().getId() + "), pois não consta nenhuma entrada em aberto");
                        }
                        break;
                }
            } else if (!entity.getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA)) {
                businessCase.getResult().setSuccess(false);
                businessCase.getResult().getMessages().add("Não pode ser cadastrada uma saída do veículo de ID (" +
                        entity.getVeiculo().getId() + ") no estabelecimento de ID (" +
                        entity.getEstabelecimento().getId() + "), pois não consta nenhuma entrada em aberto");
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}
