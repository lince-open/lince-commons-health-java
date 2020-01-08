package work.lince.commons.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import work.lince.commons.authentication.AuthenticationService;
import work.lince.commons.health.model.Health;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class HealthService {

    @Autowired
    protected AuthenticationService authenticationService;

    @Value("${lince.commons.health.serviceName:nome}")
    protected String serviceName;

    @Value("${lince.commons.health.serviceVersion:none}")
    protected String serviceVersion;

    @Value("${lince.commons.health.serviceGroup:none}")
    protected String serviceGroup;

    public Health status() {
        return Health.builder()
                .status("ok")
                .now(this.getServerDateTime())
                .user(authenticationService.getAuthenticatedUser())
                .version(serviceVersion)
                .service(serviceName)
                .group(serviceGroup)
                .build();
    }

    protected LocalDateTime getServerDateTime() {
        return LocalDateTime.now();
    }

}
