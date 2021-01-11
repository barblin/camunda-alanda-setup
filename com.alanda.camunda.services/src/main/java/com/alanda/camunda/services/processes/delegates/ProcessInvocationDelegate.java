package com.alanda.camunda.services.processes.delegates;

import com.alanda.camunda.services.processes.services.ProcessExecutionService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ProcessInvocationDelegate implements JavaDelegate {

    private final ProcessExecutionService processExecutionService;

    public ProcessInvocationDelegate(ProcessExecutionService processExecutionService) {
        this.processExecutionService = processExecutionService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        processExecutionService.execute();
    }
}
