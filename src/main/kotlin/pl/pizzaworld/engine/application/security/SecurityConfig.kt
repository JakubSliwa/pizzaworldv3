package pl.pizzaworld.engine.application.security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


class SecurityConfig(private val passwordEncoderAndMatcher: PasswordEncoder,
                     private val customUserDetailsService: CustomUserDetailsService,
                     private val authenticationService: AuthenticationService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoderAndMatcher)
    }


    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/join").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(LoginFilter("/login",
                        authenticationManager(), authenticationService),
                        UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(AuthenticationFilter(authenticationService),
                        UsernamePasswordAuthenticationFilter::class.java)
    }
}