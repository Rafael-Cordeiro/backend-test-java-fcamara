package dev.rafaelcordeiro.backendtestjavafcamara.infra.db;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoJPARepository extends JpaRepository<Veiculo, Long> {
}