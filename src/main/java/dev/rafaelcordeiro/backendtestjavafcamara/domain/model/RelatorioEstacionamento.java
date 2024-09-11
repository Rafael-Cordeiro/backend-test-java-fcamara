package dev.rafaelcordeiro.backendtestjavafcamara.domain.model;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import lombok.Data;

@Data
public class RelatorioEstacionamento extends DomainEntity {
    private Estabelecimento estabelecimento;
    private Double qtdeEntradasHora;
    private Double qtdeSaidasHora;
    private Double qtdeEntradasTotal;
    private Double qtdeSaidasTotal;
}
