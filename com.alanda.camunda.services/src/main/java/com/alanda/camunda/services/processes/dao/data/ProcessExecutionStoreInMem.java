package com.alanda.camunda.services.processes.dao.data;

import com.alanda.camunda.services.processes.dao.ProcessExecutionStore;
import com.alanda.camunda.services.processes.dao.models.Executions;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class ProcessExecutionStoreInMem implements ProcessExecutionStore {

    private static Executions executions = Executions.of(0L, LocalDateTime.now());

    @Override
    public void store(Long amount) {
        executions = Executions.of(amount, LocalDateTime.now());
    }

    @Override
    public Executions get() {
        return executions;
    }
}