package work.lince.commons.health.service

import org.springframework.boot.actuate.info.Info
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.commons.authentication.AuthenticationService
import work.lince.commons.health.config.LinceInfoContributor

import java.time.LocalDateTime
import java.time.temporal.Temporal

class LinceInfoContributorSpec extends Specification {

    LinceInfoContributor linceInfoContributor

    def setup() {
        linceInfoContributor = Spy(LinceInfoContributor)
        linceInfoContributor.authenticationService = Mock(AuthenticationService)

    }

    @Unroll
    def "status with user: #user now: #now startup: #startup"() {
        given:
            1 * linceInfoContributor.authenticationService.authenticatedUser >> { user }
            1 * linceInfoContributor.getServerDateTime() >> { now }
            1 * linceInfoContributor.getServerStartup() >> { startup }
            Info.Builder builder = new Info.Builder()
        when:
            linceInfoContributor.contribute(builder)
            def result = builder.build()

        then:
            result.get("user", String.class) == user
            result.get("now", Temporal.class) == now
            result.get("startupDate", Date.class) == startup


        where:
            user    | now                                     | startup
            "userz" | LocalDateTime.now()                     | new Date()
            "usery" | LocalDateTime.of(2020, 1, 8, 8, 27, 12) | new Date(1578940175)

    }

}