package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * <h1><code>BusinessCaseResult</code></h1>
 *
 * <p>Designada como atributo membro de {@link BusinessCase}, essa classe possui os atributos necessários para
 * sinalizar sucesso ou erro, bem como espaço para dados gerados pela execução.</p>
 *
 * <ul>
 *     <li><b>messages</b>: lista de mensagens de erro geradas ao longo da execução das strategies</li>
 *     <li><b>entity</b>: entidade opcional gerada como resposta ao sucesso de um business case</li>
 *     <li><b>entities</b>: lista de entidades opcional geradas como resposta ao sucesso de um business case</li>
 * </ul>
 *
 * @param <E> entidade que extende {@link DomainEntity}
 *
 * @see BusinessCaseResult
 * @see DomainEntity
 *
 * @author Rafael Cordeiro
 */
@Data
@NoArgsConstructor
public class BusinessCaseResult<E extends DomainEntity> {
    /**
     * Sinaliza se houve sucesso ou erro em execuções da strategy
     */
    private Boolean success;
    /**
     * Sinaliza a necessidade de interromper do fluxo do business case
     */
    private Boolean abort;
    /**
     * Lista de mensagens de erro geradas ao longo da execução das strategies
     */
    private List<String> messages;
    /**
     * Entidade opcional gerada como resposta ao sucesso de um business case
     */
    private Optional<E> entity;
    /**
     * Lista de entidades opcional geradas como resposta ao sucesso de um business case
     */
    private Optional<List<E>> entities;

    /**
     * <h1><code>getPlainMessages()</code></h1>
     *
     * Reduz lista mensagens em um texto separado por quebra de linha
     * @return String com mensagens
     */
    public String getPlainMessages() {
        return messages.stream().reduce("", (first, second) -> first.concat("\n").concat(second));
    }

    /**
     * <h1><code>addMessage()</code></h1>
     *
     * Adiciona mensagem na lista de mensagens e altera flag success para false
     * @param message mensagem a ser adicionada
     */
    public void addMessage(String message) {
        if (success) success = false;
        messages.add(message);
    }
}
