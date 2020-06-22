package pl.pizzaworld.engine.application.time

import org.springframework.stereotype.Component
import java.time.Clock
import java.time.OffsetDateTime


@Component
internal class SystemTimeProvider : TimeProvider {
    override fun now(): OffsetDateTime? {
        return OffsetDateTime.now(Clock.systemUTC())
    }
}