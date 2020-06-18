package pl.pizzaworld.engine.application.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import pl.pizzaworld.engine.application.security.CustomUserDetailsService

@RestController
class HelloController(private val customUserDetailsService: CustomUserDetailsService) {

    @GetMapping("/register")
    fun register(name: String): String {
        customUserDetailsService.registerUser()
        return "Hello $name"
    }

    @GetMapping("/hello")
    fun hello(name: String): String {
        customUserDetailsService.registerUser()
        return "Hello $name"
    }

}