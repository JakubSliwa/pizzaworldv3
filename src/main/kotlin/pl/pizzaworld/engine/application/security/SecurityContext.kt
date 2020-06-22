package pl.pizzaworld.engine.application.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Import(SecurityConfig::class)
@Configuration
class SecurityContext {

    @Bean
    fun passwordEncoderAndMatcher(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun encode(rawPassword: CharSequence?): String {
                return BCryptPasswordEncoder().encode(rawPassword)
            }

            override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
                return BCryptPasswordEncoder().matches(rawPassword, encodedPassword)
            }
        }
    }



    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("*")
        config.allowedMethods = listOf("*")
        config.allowedHeaders = listOf("*")
        config.allowCredentials = true
        config.applyPermitDefaultValues()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    @Bean
    fun authenticationService(): AuthenticationService{
        return AuthenticationService()
    }
}