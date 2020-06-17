package pl.pizzaworld.engine.application.tomcat

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer

class TomcatConfiguration : WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    override fun customize(factory: TomcatServletWebServerFactory?) {
        factory?.contextPath = "/api"
        factory?.port = 8080
    }
}