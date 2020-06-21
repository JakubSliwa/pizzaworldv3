package pl.pizzaworld.engine.application.security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

class SecurityConfig(private val passwordEncoderAndMatcher: PasswordEncoder,
                     private val customUserDetailsService: CustomUserDetailsService,
                     private val basicAuthEntryPoint: BasicAuthEntryPoint) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoderAndMatcher)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers("/join").permitAll()
                .antMatchers("/*").authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(basicAuthEntryPoint)
    }
}