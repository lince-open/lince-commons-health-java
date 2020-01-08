package work.lince.commons.health.service

import spock.lang.Specification
import spock.lang.Unroll
import work.lince.commons.authentication.AuthenticationService

import java.time.LocalDateTime

class HealthServiceSpec extends Specification {

    HealthService service

    def setup() {
        service = Spy(HealthService)
        service.authenticationService = Mock(AuthenticationService)

    }

    @Unroll
    def "status with #serviceGroup:#serviceName:#serviceVersion"() {
        given:
            1 * service.authenticationService.authenticatedUser >> { user }
            1 * service.getServerDateTime() >> { now }
            service.serviceGroup = serviceGroup
            service.serviceName = serviceName
            service.serviceVersion = serviceVersion

        when:
            def result = service.status()

        then:
            result.status == "ok"
            result.now == now
            result.user == user
            result.service == serviceName
            result.version == serviceVersion
            result.group == serviceGroup
            result.maven == "$serviceGroup:${serviceName}:${serviceVersion}"

        where:
            serviceGroup   | serviceName      | serviceVersion | user    | now
            "lince-group"  | "kvs-service"    | "1.0.0"        | "userz" | LocalDateTime.now()
            "lince-group2" | "kvs-service-v2" | "2.0.1"        | "usery" | LocalDateTime.of(2020, 1, 8, 8, 27, 12)

    }

}