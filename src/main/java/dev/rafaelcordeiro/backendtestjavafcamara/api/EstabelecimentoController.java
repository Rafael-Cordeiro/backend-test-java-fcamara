package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.core.facade.CRUDFacade;
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

import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private CRUDFacade<Estabelecimento> facade;

    @PostMapping
    public ResponseEntity<Estabelecimento> createEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        return ResponseEntity.ok(facade.persist(estabelecimento, "PERSISTE_ESTABELECIMENTO"));
    }

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> getEstabelecimento() {
        return ResponseEntity.ok(facade.findAll(new Estabelecimento(), "FINDALL_ESTABELECIMENTOS"));
    }

    @PutMapping("{id}")
    public ResponseEntity<Estabelecimento> updateEstabelecimento(@RequestBody Estabelecimento estabelecimento, @PathVariable Long id) {
        estabelecimento.setId(id);
        return ResponseEntity.ok(facade.persist(estabelecimento, "PERSISTE_ESTABELECIMENTO"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> updateEstabelecimento(@PathVariable Long id) {
        var estabelecimento = new Estabelecimento();
        estabelecimento.setId(id);
        facade.delete(estabelecimento, "DELETE_ESTABELECIMENTO");
        return ResponseEntity.ok("Deletado");
    }


}
