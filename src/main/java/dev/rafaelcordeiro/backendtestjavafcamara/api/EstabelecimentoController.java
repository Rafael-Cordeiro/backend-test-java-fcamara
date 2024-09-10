package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.core.facade.Facade;
import dev.rafaelcordeiro.backendtestjavafcamara.core.util.InvalidStrategyConditionException;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
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
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private Facade<Estabelecimento> facade;

    @PostMapping
    public ResponseEntity<APIResponse<Estabelecimento>> createEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        var result = new APIResponse<Estabelecimento>();
        try {
            result.setEntity(facade.persist(estabelecimento, "PERSISTE_ESTABELECIMENTO"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).toList());
            result.setSuccess(false);
            return ResponseEntity.unprocessableEntity().body(result);
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<Estabelecimento>> getEstabelecimento() {
        var result = new APIResponse<Estabelecimento>();
        try {
            result.setEntities(facade.findAll(new Estabelecimento(), "FINDALL_ESTABELECIMENTOS"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).toList());
            result.setSuccess(false);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<APIResponse<Estabelecimento>> deleteEstabelecimento(@RequestBody Estabelecimento estabelecimento, @PathVariable Long id) {
        estabelecimento.setId(id);
        var result = new APIResponse<Estabelecimento>();
        try {
            result.setEntity(facade.persist(estabelecimento, "PERSISTE_ESTABELECIMENTO"));
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).toList());
            result.setSuccess(false);
            return ResponseEntity.unprocessableEntity().body(result);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Estabelecimento>> deleteEstabelecimento(@PathVariable Long id) {
        var estabelecimento = new Estabelecimento();
        estabelecimento.setId(id);
        var result = new APIResponse<Estabelecimento>();
        try {
            facade.delete(estabelecimento, "DELETE_ESTABELECIMENTO");
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } catch (InvalidStrategyConditionException e) {
            result.setMessages(Arrays.stream(e.getMessage().split("\n")).toList());
            result.setSuccess(false);
            return ResponseEntity.status(404).body(result);
        }
    }


}
