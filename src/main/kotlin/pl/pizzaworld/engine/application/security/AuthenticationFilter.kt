package pl.pizzaworld.engine.application.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


class AuthenticationFilter(private val authenticationService: AuthenticationService) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val authentication: Authentication? = authenticationService.getAuthentication(request as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}