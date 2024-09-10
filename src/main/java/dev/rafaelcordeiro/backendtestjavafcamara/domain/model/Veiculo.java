package dev.rafaelcordeiro.backendtestjavafcamara.domain.model;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "veiculo")
@Data
public class Veiculo extends DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vei_id")
    private Long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String tipo;
}
