package work.lince.commons.health.model;

import lombok.*;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Health {

    private String status;
    private LocalDateTime now;
    private String user;
    private String service;
    private String version;
    private String group;

    public String getMaven() {
        return String.format("%s:%s:%s", group, service, version);
    }

}
