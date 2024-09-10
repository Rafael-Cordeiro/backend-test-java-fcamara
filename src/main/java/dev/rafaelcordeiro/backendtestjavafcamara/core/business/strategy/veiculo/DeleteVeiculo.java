package dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.veiculo;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.BusinessCase;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.infra.db.VeiculoJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteVeiculo implements IStrategy<Veiculo> {

    @Autowired
    VeiculoJPARepository repository;

    @Override
    public void execute(Veiculo entity, BusinessCase<Veiculo> businessCase) {
        try {
            if (businessCase.getResult().getSuccess()) {
                if (entity.getId() != null) {
                    var persistedEntity = repository.findById(entity.getId()).orElse(null);
                    if (persistedEntity != null) {
                        repository.delete(persistedEntity);
                    } else throw new Exception("A entidade analisada possui ID (" + entity.getId() + "), mas nenhum registro foi encontrado no banco de dados");
                }
                else throw new Exception("A entidade analisada não possui ID");
            } else {
                log.error("Status de erro na execução das validações. Abortando exclusão de veículo");
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}

