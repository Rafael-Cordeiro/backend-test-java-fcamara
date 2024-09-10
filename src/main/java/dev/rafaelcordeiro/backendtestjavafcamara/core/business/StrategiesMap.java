package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.DeleteEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.DeleteVeiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.FindAllVeiculos;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.PersisteEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.FindAllEstabelecimentos;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.PersisteVeiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.ValidaFormEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.ValidaFormVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StrategiesMap {

    @Autowired
    ValidaFormEstabelecimento validaFormEstabelecimento;
    @Autowired
    PersisteEstabelecimento persisteEstabelecimento;
    @Autowired
    FindAllEstabelecimentos findAllEstabelecimentos;
    @Autowired
    DeleteEstabelecimento deleteEstabelecimento;
    @Autowired
    ValidaFormVeiculo validaFormVeiculo;
    @Autowired
    PersisteVeiculo persisteVeiculo;
    @Autowired
    FindAllVeiculos findAllVeiculos;
    @Autowired
    DeleteVeiculo deleteVeiculo;


    private Map<String, List<IStrategy>> map;

    private void loadMap() {
        map = new HashMap<>();
        map.put("PERSISTE_ESTABELECIMENTO", List.of(validaFormEstabelecimento, persisteEstabelecimento));
        map.put("FINDALL_ESTABELECIMENTOS", List.of(findAllEstabelecimentos));
        map.put("DELETE_ESTABELECIMENTO", List.of(deleteEstabelecimento));
        map.put("PERSISTE_VEICULO", List.of(validaFormVeiculo, persisteVeiculo));
        map.put("FINDALL_VEICULOS", List.of(findAllVeiculos));
        map.put("DELETE_VEICULO", List.of(deleteVeiculo));
    }

    public List<IStrategy> getStrategies(String name) {
        if (map == null) {
            loadMap();
        }
        return map.get(name);
    }
}
