package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <h1><code>BusinessCase</code></h1>
 *
 * <p>Passada através da execução das strategies, esta classe concentra a gestão do business case executado,
 * contendo o nome do business case, metadados e dados referentes ao resultado da execução.</p>
 *
 * @param <E> entidade que extende {@link DomainEntity}
 *
 * @see BusinessCaseResult
 * @see DomainEntity
 *
 * @author Rafael Cordeiro
 */
@Data
public class BusinessCase<E extends DomainEntity> {
    private String name;
    private BusinessCaseResult<E> result;
    private Map<String, Object> metadata;

    /**
     * <h1><code>abortExecution()</code></h1>
     *
     * Altera flags de sucesso e abort para interromper execução da pipeline
     * @param msg mensagem atribuída a essa operação (Geralmente mensagem de excepiton)
     */
    public void abortExecution(String msg) {
        result.setAbort(true);
        result.setSuccess(false);
        result.setEntity(Optional.empty());
        result.getMessages().add("Exception: ".concat(msg));
    }

    /**
     * <h1><code>builder()</code></h1>
     *
     * @return Nova instância de {@link BusinessCaseBuilder}
     */
    public BusinessCaseBuilder<E> builder() {
        return new BusinessCaseBuilder<E>();
    }

    /**
     * <h1><code>BusinessCaseBuilder</code></h1>
     *
     * <p>Classe builder (design pattern) que constrói nova instância de {@link BusinessCase} com valores padronizados
     * e nome parametrizado.</p>
     *
     * @param <E> entidade que extende {@link DomainEntity}
     *
     * @author Rafael Cordeiro
     */
    public static class BusinessCaseBuilder<E extends DomainEntity> {
        private BusinessCase<E> businessCase;
        public BusinessCaseBuilder() {
            businessCase = new BusinessCase<>();
        }

        public BusinessCaseBuilder<E> withName(String name) {
            businessCase.name = name;
            return this;
        }

        public BusinessCase<E> build() {
            businessCase.result = new BusinessCaseResult();
            businessCase.result.setSuccess(true);
            businessCase.result.setAbort(false);
            businessCase.result.setMessages(new ArrayList<>());
            businessCase.metadata = new HashMap<>();
            return businessCase;
        }
    }
}
