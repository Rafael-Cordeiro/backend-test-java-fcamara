package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.relatorioestacionamento;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.RelatorioEstacionamento;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.EstabelecimentoJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1><code>ComplementarEstabelecimento</code></h1>
 *
 * <p>Strategy que complementa o estabelecimento da requisão de relatório / aponta erro se não existir o estabelecimento
 * com o ID informado.</p>
 *
 * @author Rafael Cordeiro
 */
@Component
@Slf4j
public class ComplementarEstabelecimento implements IStrategy<RelatorioEstacionamento> {

    @Autowired
    EstabelecimentoJPARepository repository;

    @Override
    public void execute(RelatorioEstacionamento entity, BusinessCase<RelatorioEstacionamento> businessCase) {
        try {
            repository.findById(entity.getEstabelecimento().getId()).ifPresentOrElse(
                    it -> entity.setEstabelecimento(it),
                    () -> businessCase.getResult().addMessage("Estabelecimento não encontrado"));
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}
