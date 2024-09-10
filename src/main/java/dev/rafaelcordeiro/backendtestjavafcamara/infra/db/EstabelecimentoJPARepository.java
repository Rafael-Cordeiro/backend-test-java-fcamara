package dev.rafaelcordeiro.backendtestjavafcamara.infra.db;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoJPARepository extends JpaRepository<Estabelecimento, Long> {
}
