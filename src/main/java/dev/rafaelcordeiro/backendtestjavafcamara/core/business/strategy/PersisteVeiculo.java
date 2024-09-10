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
public class PersisteVeiculo implements IStrategy<Veiculo> {

    @Autowired
    VeiculoJPARepository repository;

    @Override
    public void execute(Veiculo entity, BusinessCase<Veiculo> businessCase) {
        try {
            if (businessCase.getResult().getSuccess()) {
                if (entity.getId() != null) {
                    var persistedEntity = repository.findById(entity.getId()).orElse(null);
                    if (persistedEntity != null) {
                        persistedEntity.setMarca(entity.getMarca());
                        persistedEntity.setModelo(entity.getModelo());
                        persistedEntity.setCor(entity.getCor());
                        persistedEntity.setPlaca(entity.getPlaca());
                        persistedEntity.setTipo(entity.getTipo());

                        businessCase.getResult().setEntity(Optional.of(repository.save(persistedEntity)));
                    } else throw new Exception("A entidade analisada possui ID (" + entity.getId() + "), mas nenhum registro foi encontrado no banco de dados");
                }
                else businessCase.getResult().setEntity(Optional.of(repository.save(entity)));
            } else {
                log.error("Status de erro na execução das validações. Abortando persistência de Veículo");
            }
        } catch (Exception e) {
            handleException(log, e, businessCase);
        }
    }
}