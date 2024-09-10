package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.core.facade.Facade;
import dev.rafaelcordeiro.backendtestjavafcamara.core.util.InvalidStrategyConditionException;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private Facade<Veiculo> facade;

    @PostMapping
    public ResponseEntity<APIResponse<Veiculo>> createVeiculo(@RequestBody Veiculo veiculo) {
        var result = new APIResponse<Veiculo>();
        try {
            result.setEntity(facade.persist(veiculo, "PERSISTE_VEICULO"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).filter(it -> !it.isEmpty()).toList());
            result.setSuccess(false);
            return ResponseEntity.unprocessableEntity().body(result);
        }
    }
    
    @GetMapping
    public ResponseEntity<APIResponse<Veiculo>> getVeiculo() {
        var result = new APIResponse<Veiculo>();
        try {
            result.setEntities(facade.findAll(new Veiculo(), "FINDALL_VEICULOS"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).filter(it -> !it.isEmpty()).toList());
            result.setSuccess(false);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<APIResponse<Veiculo>> updateVeiculo(@RequestBody Veiculo veiculo, @PathVariable Long id) {
        veiculo.setId(id);
        var result = new APIResponse<Veiculo>();
        try {
            result.setEntity(facade.persist(veiculo, "PERSISTE_VEICULO"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).filter(it -> !it.isEmpty()).toList());
            result.setSuccess(false);
            return ResponseEntity.unprocessableEntity().body(result);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Veiculo>> deleteVeiculo(@PathVariable Long id) {
        var Veiculo = new Veiculo();
        Veiculo.setId(id);
        var result = new APIResponse<Veiculo>();
        try {
            facade.delete(Veiculo, "DELETE_VEICULO");
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).filter(it -> !it.isEmpty()).toList());
            result.setSuccess(false);
            return ResponseEntity.status(404).body(result);
        }
    }
}
