package com.alanda.process.service.files.data.camunda;

import com.alanda.process.service.config.ProcessManagerConfig;
import com.alanda.process.service.files.data.FileStore;
import com.alanda.process.service.files.data.models.ProcessFileDto;
import com.alanda.process.service.files.exceptions.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
class FileStoreCamunda implements FileStore {

    private static final String PROCESS_FILE_LABEL = "process.bpmn";
    private static final String DEPLOYMENT_NAME_LABEL = "deployment-name";
    private static final String ENABLE_DUPLICATE_FILTERING_LABEL = "enable-duplicate-filtering";
    private static final String DEPLOYMENT_CHANGED_ONLY = "deploy-changed-only";

    private final ProcessManagerConfig config;

    @Autowired
    FileStoreCamunda(ProcessManagerConfig config) {
        this.config = config;
    }

    @Override
    public String upload(ProcessFileDto processFileDto) throws FileUploadException {
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add(PROCESS_FILE_LABEL, new FileSystemResource(processFileDto.getFile()));
        body.add(DEPLOYMENT_NAME_LABEL, processFileDto.getName());
        body.add(ENABLE_DUPLICATE_FILTERING_LABEL, processFileDto.getName());
        body.add(DEPLOYMENT_CHANGED_ONLY, processFileDto.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String serverUrl = config.getApi() + config.getUpload();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                throw new Exception(response.getBody());
            }
            return "upload-success";
        } catch (Exception ex) {
            throw new FileUploadException(ex.getMessage());
        }
    }
}
