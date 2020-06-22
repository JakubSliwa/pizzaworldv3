package pl.pizzaworld.engine.application.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.pizzaworld.engine.application.security.CustomUserDetailsService
import pl.pizzaworld.engine.person.Credentials
import pl.pizzaworld.engine.person.NewPerson
import java.util.*

@RestController
class HelloController(val customUserDetailsService: CustomUserDetailsService) {

    @PostMapping("/login")
    fun login(@RequestBody credentials: Credentials): String {

        val loadUserByUsername = customUserDetailsService.loadUserByUsername(credentials.username)
        return "Hello ${loadUserByUsername.username} , ${loadUserByUsername.password}"
    }

    @PostMapping("/join")
    fun register(@RequestBody newPerson: NewPerson): UUID {
        newPerson.validate()
        return customUserDetailsService.registerUser(newPerson)
    }
}