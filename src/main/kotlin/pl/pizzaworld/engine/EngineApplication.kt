package pl.pizzaworld.engine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import pl.pizzaworld.engine.application.security.SecurityContext
import pl.pizzaworld.engine.application.tomcat.TomcatConfiguration

@SpringBootApplication
@Import(SecurityContext::class,
        TomcatConfiguration::class)
class EngineApplication

fun main(args: Array<String>) {
    runApplication<EngineApplication>(*args)
}
