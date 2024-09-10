package dev.rafaelcordeiro.backendtestjavafcamara.api;

import dev.rafaelcordeiro.backendtestjavafcamara.domain.DomainEntity;
import lombok.Data;

import java.util.List;

@Data
public class APIResponse<E extends DomainEntity> {
    private Boolean success;
    private List<String> messages;
    private E entity;
    private List<E> entities;
}
