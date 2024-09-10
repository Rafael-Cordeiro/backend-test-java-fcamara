package dev.rafaelcordeiro.backendtestjavafcamara.core.business;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class BusinessCaseResult<E extends DomainEntity> {
    private Boolean success;
    private Boolean abort;
    private List<String> messages;
    private Optional<E> entity;
    private Optional<List<E>> entities;

    public String getPlainMessages() {
        return messages.stream().reduce("", (first, second) -> first.concat("\n").concat(second));
    }

    public void addMessage(String message) {
        if (success) success = false;
        messages.add(message);
    }
}
