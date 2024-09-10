package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.DeleteEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.PersisteEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.FindAllEstabelecimentos;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.ValidaFormEstabelecimento;
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

    private Map<String, List<IStrategy>> map;
    private void loadMap() {
        map = new HashMap<>();
        map.put("PERSISTE_ESTABELECIMENTO", List.of(validaFormEstabelecimento, persisteEstabelecimento));
        map.put("FINDALL_ESTABELECIMENTOS", List.of(findAllEstabelecimentos));
        map.put("DELETE_ESTABELECIMENTO", List.of(deleteEstabelecimento));
    }

    public List<IStrategy> getStrategies(String name) {
        if (map == null) {
            loadMap();
        }
        return map.get(name);
    }
}
