package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class BusinessCase<E extends DomainEntity> {
    private String name;
    private BusinessCaseResult<E> result;
    private Map<String, Object> metadata;

    public void abortExecution(String msg) {
        result.setAbort(true);
        result.setSuccess(false);
        result.setEntity(Optional.empty());
        result.getMessages().add("Exception: ".concat(msg));
    }

    public BusinessCaseBuilder<E> builder() {
        return new BusinessCaseBuilder<E>();
    }

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
