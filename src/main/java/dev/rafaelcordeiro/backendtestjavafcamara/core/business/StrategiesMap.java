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

/**
 * <h1><code>StrategiesMap</code></h1>
 *
 * <p>Classe wrapper para mapa de strategies. As strategies são organizadas em listas, que são mapeadas com strings,
 * que nomeiam os business cases</p>
 *
 * <p>Cada business case é uma pipeline de tarefas que utilizam o design pattern Strategy. Cada classe strategy
 * recebe a entidade e um {@link BusinessCase}.</p>
 *
 * @see BusinessCase
 * @see BusinessCaseResult
 * @see BusinessCasesExecutor
 *
 * @author Rafael Cordeiro
 */
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

    /**
     * <h1><code>loadMap()</code></h1>
     *
     * Carrega mapa com pipelines de strategies
     *
     * @author Rafael Cordeiro
     */
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

    /**
     * <h1><code>getStrategies()</code></h1>
     *
     * Retorna pipeline de strategies e cria mapa se ainda não tiver sido instanciado.
     *
     * @param name Nome do business case
     * @author Rafael Cordeiro
     */
    public List<IStrategy> getStrategies(String name) {
        if (map == null) {
            loadMap();
        }
        return map.get(name);
    }
}
