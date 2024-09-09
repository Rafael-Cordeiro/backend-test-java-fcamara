package dev.rafaelcordeiro.backendtestjavafcamara.domain.model;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "estabelecimento")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento extends DomainEntity {
    private String nome;
    private String cnpj;
    private String endereço;
    private String telefone;
    private Integer qtdeVagasMotos;
    private Integer qtdeVagasCarros;

}
