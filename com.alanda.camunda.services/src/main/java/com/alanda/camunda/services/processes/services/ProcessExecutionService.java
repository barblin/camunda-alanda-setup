package com.alanda.camunda.services.processes.services;

import com.alanda.camunda.services.processes.dao.ProcessExecutionStore;
import com.alanda.camunda.services.processes.dao.models.Executions;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessExecutionService {
    private final TaskService taskService;
    private final ManagementService managementService;
    private final ProcessExecutionStore store;

    @Autowired
    ProcessExecutionService(TaskService taskService, ManagementService managementService, ProcessExecutionStore store) {
        this.taskService = taskService;
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
        return taskService.createNativeTaskQuery()
                .sql("SELECT count(*) FROM " + managementService.getTableName(EventSubscription.class))
                .count();
    }
}