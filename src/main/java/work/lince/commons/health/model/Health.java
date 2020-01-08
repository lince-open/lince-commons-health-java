package work.lince.commons.health.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
