package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.core.facade.Facade;
import dev.rafaelcordeiro.backendtestjavafcamara.core.util.InvalidStrategyConditionException;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.RelatorioEstacionamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/relatorioestabelecimento")
public class RelatorioEstabelecimentoController {

    @Autowired
    private Facade<RelatorioEstacionamento> facade;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<RelatorioEstacionamento>> getEstabelecimento(@PathVariable Long id) {
        var result = new APIResponse<RelatorioEstacionamento>();
        try {
            var relatorio = new RelatorioEstacionamento();
            relatorio.setEstabelecimento(new Estabelecimento());
            relatorio.getEstabelecimento().setId(id);
            result.setEntity(facade.persist(relatorio, "GERA_RELATORIO_ESTABELECIMENTO"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).filter(it -> !it.isEmpty()).toList());
            result.setSuccess(false);
            return ResponseEntity.badRequest().body(result);
        }
    }
}
