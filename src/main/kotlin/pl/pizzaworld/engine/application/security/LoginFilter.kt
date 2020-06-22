package pl.pizzaworld.engine.application.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import pl.pizzaworld.engine.person.Credentials
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginFilter(url: String?,
                  authManager: AuthenticationManager?,
                  private val authenticationService: AuthenticationService) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {
    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(
            req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val creds: Credentials = ObjectMapper()
                .readValue(req.inputStream, Credentials::class.java)
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        creds.username,
                        creds.password, emptyList())
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
            req: HttpServletRequest,
            res: HttpServletResponse, chain: FilterChain,
            auth: Authentication) {
        authenticationService.addToken(res, auth.name)
    }

    init {
        authenticationManager = authManager
    }
}