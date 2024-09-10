package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida.CadastrarEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida.ComplementarVeiculoEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.estabelecimento.DeleteEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.veiculo.DeleteVeiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.veiculo.FindAllVeiculos;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.estabelecimento.PersisteEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.estabelecimento.FindAllEstabelecimentos;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.IStrategy;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.veiculo.PersisteVeiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.estabelecimento.ValidaFormEstabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.veiculo.ValidaFormVeiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida.ValidarEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.core.business.strategy.controleentradasaida.VerificarDisponibilidadeVaga;
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
    @Autowired
    ComplementarVeiculoEstabelecimento complementarVeiculoEstabelecimento;
    @Autowired
    ValidarEntradaSaida validarEntradaSaida;
    @Autowired
    VerificarDisponibilidadeVaga verificarDisponibilidadeVaga;
    @Autowired
    CadastrarEntradaSaida cadastrarEntradaSaida;


    private Map<String, List<IStrategy>> map;

    private void loadMap() {
        map = new HashMap<>();
        map.put("PERSISTE_ESTABELECIMENTO", List.of(validaFormEstabelecimento, persisteEstabelecimento));
        map.put("FINDALL_ESTABELECIMENTOS", List.of(findAllEstabelecimentos));
        map.put("DELETE_ESTABELECIMENTO", List.of(deleteEstabelecimento));
        map.put("PERSISTE_VEICULO", List.of(validaFormVeiculo, persisteVeiculo));
        map.put("FINDALL_VEICULOS", List.of(findAllVeiculos));
        map.put("DELETE_VEICULO", List.of(deleteVeiculo));
        map.put("CADASTRA_ENTRADA_SAIDA", List.of(complementarVeiculoEstabelecimento, validarEntradaSaida, verificarDisponibilidadeVaga, cadastrarEntradaSaida));
    }

    public List<IStrategy> getStrategies(String name) {
        if (map == null) {
            loadMap();
        }
        return map.get(name);
    }
}
