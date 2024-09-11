package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.relatorioestacionamento;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.EntradaSaidaEnum;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.RelatorioEstacionamento;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.ControleEntradaSaidaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1><code>GerarRelatorioEstacionamento</code></h1>
 *
 * <p>Strategy que gera relatório de estabelecimento contendo informações sobre o fluxo de entradas e saídas.</p>
 *
 * @author Rafael Cordeiro
 */
@Component
@Slf4j
public class GerarRelatorioEstacionamento implements IStrategy<RelatorioEstacionamento> {

    @Autowired
    ControleEntradaSaidaRepository repository;

    @Override
    public void execute(RelatorioEstacionamento entity, BusinessCase<RelatorioEstacionamento> businessCase) {
        try {
            if (businessCase.getResult().getSuccess()) {
                var entradasSaidas = repository.findByEstabelecimento(entity.getEstabelecimento());

                if (entradasSaidas.size() > 0) {
                    var mapEntradasHora = entradasSaidas.stream()
                            .filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA))
                            .collect(Collectors.groupingBy(
                                    it -> LocalDateTime.ofInstant(it.getRegistro(), ZoneId.systemDefault()).getHour()));

                    var mapSaidasHora = entradasSaidas.stream()
                            .filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.SAIDA))
                            .collect(Collectors.groupingBy(
                                    it -> LocalDateTime.ofInstant(it.getRegistro(), ZoneId.systemDefault()).getHour()));

                    entity.setQtdeEntradasHora(
                            Double.valueOf(mapEntradasHora.values().stream().map(List::size).reduce(Integer::sum).orElse(1))
                                    / mapEntradasHora.size()
                    );
                    entity.setQtdeSaidasHora(
                            Double.valueOf(mapSaidasHora.values().stream().map(List::size).reduce(Integer::sum).orElse(1))
                                    / mapEntradasHora.size()
                    );
                    entity.setQtdeEntradasTotal(Double.valueOf(mapEntradasHora.values().stream().map(List::size).reduce(Integer::sum).orElse(1)));
                    entity.setQtdeSaidasTotal(Double.valueOf(mapSaidasHora.values().stream().map(List::size).reduce(Integer::sum).orElse(1)));

                    var entradasSaidasCarro = entradasSaidas
                            .stream().filter(it -> it.getVeiculo().getTipo().equals("B")).toList();
                    var entradasSaidasMoto = entradasSaidas
                            .stream().filter(it -> it.getVeiculo().getTipo().equals("A")).toList();

                    entity.setVagasCarroOcupadas(entradasSaidasCarro.stream().filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA)).count() -
                            entradasSaidasCarro.stream().filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.SAIDA)).count());
                    entity.setVagasMotoOcupadas(entradasSaidasMoto.stream().filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.ENTRADA)).count() -
                            entradasSaidasMoto.stream().filter(it -> it.getEntradaSaida().equals(EntradaSaidaEnum.SAIDA)).count());

                    businessCase.getResult().setEntity(Optional.of(entity));
                } else {
                    businessCase.getResult().addMessage("Não há registro de entradas e saídas para esse estabelecimento (ID " + entity.getEstabelecimento().getId() + ")");
                }
            } else {
                log.error("Status de erro na execução das validações. Abortando criação de relatório");
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}