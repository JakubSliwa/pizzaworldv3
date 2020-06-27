package pl.pizzaworld.engine.application.api

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.pizzaworld.engine.application.security.CustomUserDetailsService
import pl.pizzaworld.engine.person.Credentials
import pl.pizzaworld.engine.person.NewPerson
import java.util.*

@RestController
class HelloController(val customUserDetailsService: CustomUserDetailsService) {

    @PostMapping("/login", consumes = ["application/json"], produces = ["application/json"])
    fun login(@RequestBody credentials: Credentials): String {

        val loadUserByUsername = customUserDetailsService.loadUserByUsername(credentials.username)
        return "Hello ${loadUserByUsername?.username} , ${loadUserByUsername?.password}"
    }

    @PostMapping("/join")
    fun register(@RequestBody newPerson: NewPerson): UUID {
        newPerson.validate()
        return customUserDetailsService.registerUser(newPerson)
    }

    @PostMapping("/logout")
    fun register(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val authentication = SecurityContextHolder.getContext().getAuthentication()
        return "OK"
    }
}