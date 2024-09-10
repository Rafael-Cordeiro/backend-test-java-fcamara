package dev.rafaelcordeiro.backendtestjavafcamara.infra.db;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.ControleEntradaSaida;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Estabelecimento;
import dev.rafaelcordeiro.backendtestjavafcamara.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControleEntradaSaidaRepository extends JpaRepository<ControleEntradaSaida, Long> {
    List<ControleEntradaSaida> findByVeiculoAndEstabelecimento(Veiculo veiculo, Estabelecimento estabelecimento);
    List<ControleEntradaSaida> findByEstabelecimento(Estabelecimento estabelecimento);
}