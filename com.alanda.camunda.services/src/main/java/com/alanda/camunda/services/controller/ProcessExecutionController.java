package com.alanda.camunda.services.controller;

import com.alanda.camunda.services.processes.dao.models.Executions;
import com.alanda.camunda.services.processes.services.ProcessExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcessExecutionController {

    private final ProcessExecutionService processExecutionService;

    @Autowired
    ProcessExecutionController(ProcessExecutionService processExecutionService) {
        this.processExecutionService = processExecutionService;
    }

    @GetMapping("/api/processes/executions")
    public ResponseEntity<Executions> getProcessExecutions() {
        return ResponseEntity.ok(processExecutionService.get());
    }
}
