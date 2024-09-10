package dev.rafaelcordeiro.backendtestjavafcamara.domain.model;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "estabelecimento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento extends DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="est_id")
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private Integer qtdeVagasMotos;
    private Integer qtdeVagasCarros;

}
