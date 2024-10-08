package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.ControleEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.EstabelecimentoJPARepository;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.VeiculoJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <h1><code>ComplementarVeiculoEstabelecimento</code></h1>
 *
 * Strategy que complementa a entidade {@link ControleEntradaSaida} com dados de {@link Veiculo} e
 * {@link Estabelecimento} e reporta erro se não encotrar qualquer um desses.
 *
 * @author Rafael Cordeiro
 */
@Component
@Slf4j
public class ComplementarVeiculoEstabelecimento implements IStrategy<ControleEntradaSaida> {

    @Autowired
    EstabelecimentoJPARepository estabRepository;
    @Autowired
    VeiculoJPARepository veicRepository;

    @Override
    public void execute(ControleEntradaSaida entity, BusinessCase<ControleEntradaSaida> businessCase) {
        try {
            Optional<Estabelecimento> estab = estabRepository.findById(entity.getEstabelecimento().getId());
            estab.ifPresentOrElse(entity::setEstabelecimento, () -> {
                businessCase.abortExecution("O estabelecimento (ID " + entity.getEstabelecimento().getId() +
                        ") informado não foi encontrado");
            });
            Optional<Veiculo> veic = veicRepository.findById(entity.getVeiculo().getId());
            veic.ifPresentOrElse(entity::setVeiculo, () -> {
                businessCase.abortExecution("O veículo (ID " + entity.getVeiculo().getId() +
                        ") informado não foi encontrado");
            });
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}