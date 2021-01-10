package com.alanda.camunda.services.processes.dao;

import com.alanda.camunda.services.processes.dao.models.Executions;

public interface ProcessExecutionStore {

    void store(Long amount);

    Executions get();
}
