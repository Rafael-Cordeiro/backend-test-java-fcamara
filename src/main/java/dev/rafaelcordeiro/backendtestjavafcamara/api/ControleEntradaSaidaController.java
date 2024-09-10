package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.core.facade.Facade;
import dev.rafaelcordeiro.backendtestjavafcamara.core.util.InvalidStrategyConditionException;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.ControleEntradaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/controleentradasaida")
public class ControleEntradaSaidaController {

    @Autowired
    private Facade<ControleEntradaSaida> facade;

    @PostMapping
    public ResponseEntity<APIResponse<ControleEntradaSaida>> cadastraEntradaSaida(@RequestBody ControleEntradaSaida ControleEntradaSaida) {
        var result = new APIResponse<ControleEntradaSaida>();
        try {
            result.setEntity(facade.persist(ControleEntradaSaida, "CADASTRA_ENTRADA_SAIDA"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).filter(it -> !it.isEmpty()).toList());
            result.setSuccess(false);
            return ResponseEntity.unprocessableEntity().body(result);
        }
    }
}
