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
 * <h1><code>PersisteEstabelecimento</code></h1>
 *
 * Strategy que realiza o cadastro ou atualização de {@link Estabelecimento}.
 *
 * @author Rafael Cordeiro
 */
@Component
@Slf4j
public class PersisteEstabelecimento implements IStrategy<Estabelecimento> {

    @Autowired
    EstabelecimentoJPARepository repository;

    @Override
    public void execute(Estabelecimento entity, BusinessCase<Estabelecimento> businessCase) {
        try {
            if (businessCase.getResult().getSuccess()) {
                if (entity.getId() != null) {
                    var persistedEntity = repository.findById(entity.getId()).orElse(null);
                    if (persistedEntity != null) {
                        persistedEntity.setNome(entity.getNome());
                        persistedEntity.setCnpj(entity.getCnpj());
                        persistedEntity.setEndereco(entity.getEndereco());
                        persistedEntity.setTelefone(entity.getTelefone());
                        persistedEntity.setQtdeVagasMotos(entity.getQtdeVagasMotos());
                        persistedEntity.setQtdeVagasCarros(entity.getQtdeVagasCarros());

                        businessCase.getResult().setEntity(Optional.of(repository.save(persistedEntity)));
                    } else throw new Exception("A entidade analisada possui ID (" + entity.getId() + "), mas nenhum registro foi encontrado no banco de dados");
                }
                else businessCase.getResult().setEntity(Optional.of(repository.save(entity)));
            } else {
                log.error("Status de erro na execução das validações. Abortando persistência de Estabelecimento");
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}
