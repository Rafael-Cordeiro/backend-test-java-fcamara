package dev.rafaelcordeiro.backendtestjavafcamara.domain.model;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "controle_entrada_saida")
public class ControleEntradaSaida extends DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id")
    private Estabelecimento estabelecimento;
    @ManyToOne
    @JoinColumn(name = "id")
    private Veiculo veiculo;
    private EntradaSaidaEnum entradaSaida;
    private Date registro;
}