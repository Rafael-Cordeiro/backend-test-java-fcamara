package dev.rafaelcordeiro.backendtestjavafcamara.core.facade;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {CRUDFacade.class})
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CreateEstabelecimentoTest {

    @Autowired
    @InjectMocks
    private CRUDFacade<Estabelecimento> facade;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Valida se bean facade não é nulo")
    public void assert_that_facade_not_null() {
        assertNotNull(facade);
    }

    @Test
    public void should_be_able_to_create_estabelecimento() {
        // criar novo estab
        // criar tabela estab (Entidade)
        Estabelecimento estabelecimento = new Estabelecimento(
                1l,
                "Estapar",
                "12.345.678/0001-90",
                "Rua cinco - São Paulo",
                "(11) 98765-4321",
                25,
                15
        );

//        var fachada = new CRUDFacade<Estabelecimento>();
        Estabelecimento createdEstabelecimento = facade.persist(estabelecimento, "PERSISTE_ESTABELECIMENTO");

        assertNotNull(createdEstabelecimento);
        // criar repository estab
    }

}