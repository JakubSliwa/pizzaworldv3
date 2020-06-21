package pl.pizzaworld.engine.application.security

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED


class BasicAuthEntryPoint : AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, e: AuthenticationException?) {
        response.addHeader("WWW-Authenticate", "Basic realm=pizzaworld")
        response.status = SC_UNAUTHORIZED
        if (e is BadCredentialsException) {
            println("Bad credentials.")
        } else if (e is InsufficientAuthenticationException) {
            println("No credentials.")
        }
    }


}