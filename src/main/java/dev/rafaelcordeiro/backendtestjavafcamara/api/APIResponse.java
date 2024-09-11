package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import lombok.Data;

import java.util.List;

/**
 * <h1><code>APIResponse</code></h1>
 *
 * Modelo de resposta da API, que agrega status de sucesso, lista de mensagens e dados de entidades.
 * @param <E> entidade que extende {@link DomainEntity}
 *
 * @author Rafael Cordeiro
 */
@Data
public class APIResponse<E extends DomainEntity> {
    private Boolean success;
    private List<String> messages;
    private E entity;
    private List<E> entities;
}
