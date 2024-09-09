package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEstabelecimentoTest {

    @Test
    public void should_be_able_to_create_estabelecimento() {
        // criar novo estab
        // criar tabela estab (Entidade)
        Estabelecimento estabelecimento = new Estabelecimento(
                "Estapar",
                "12.345.678/0001-90",
                "Rua cinco - São Paulo",
                "(11) 98765-4321",
                25,
                15
        );

        // criar service
        CRUDFacade<Estabelecimento> facade = new CRUDFacade<>();
        Estabelecimento createdEstabelecimento = facade.create(estabelecimento);

        assertNotNull(createdEstabelecimento);
        // criar repository estab
    }

}