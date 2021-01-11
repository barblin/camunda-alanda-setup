package com.alanda.camunda.services.processes.dao.models;

import java.time.LocalDateTime;

public final class Executions {
    private final Long amount;
    private final LocalDateTime timestamp;

    private Executions(Long amount, LocalDateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public static Executions of(Long amount, LocalDateTime timestamp) {
        return new Executions(amount, timestamp);
    }

    public Long getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
