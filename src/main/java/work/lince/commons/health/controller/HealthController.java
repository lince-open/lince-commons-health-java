package work.lince.commons.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.commons.health.model.Health;
import work.lince.commons.health.service.HealthService;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = {"/", "/health"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthController {

    @Autowired
    protected HealthService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Health health() {
        return service.status();
    }


}