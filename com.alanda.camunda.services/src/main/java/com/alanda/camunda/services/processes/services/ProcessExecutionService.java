package com.alanda.camunda.services.processes.services;

import com.alanda.camunda.services.processes.dao.ProcessExecutionStore;
import com.alanda.camunda.services.processes.dao.models.Executions;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessExecutionService {
    private final ManagementService managementService;
    private final ProcessExecutionStore store;

    @Autowired
    ProcessExecutionService(ManagementService managementService, ProcessExecutionStore store) {
        this.managementService = managementService;
        this.store = store;
    }

    public void execute() {
        store.store(getExecutions());
    }

    public Executions get() {
        return store.get();
    }

    private Long getExecutions() {
        return managementService
                .createMetricsQuery()
                .name(Metrics.ROOT_PROCESS_INSTANCE_START)
                .sum();
    }
}