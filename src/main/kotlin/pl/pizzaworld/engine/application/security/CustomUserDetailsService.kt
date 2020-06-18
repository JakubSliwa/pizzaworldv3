package pl.pizzaworld.engine.application.security


import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
open class CustomUserDetailsService(private val userRepository: UserRepository,
                                    private val  encoder: PasswordEncoder) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return CustomUserDetails(userRepository.findOneByUserName(username)!!)
    }

    fun registerUser() {
        val user = User("jakub", "test", "js", "js@a", encoder.encode("1qaz2wsx"))
        userRepository.save(user)
    }

}
