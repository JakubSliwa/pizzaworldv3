package pl.pizzaworld.engine.application.security


import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.pizzaworld.engine.person.NewPerson
import java.util.*

@Service
class CustomUserDetailsService(private val userRepository: UserRepository,
                               private val encoder: PasswordEncoder) : UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails {
        return CustomUserDetails(userRepository.findOneByUserName(username)!!)
    }

    fun registerUser(newPerson: NewPerson): UUID {
        val user = User(newPerson.firstName, newPerson.lastName, newPerson.userName, newPerson.email, encoder.encode(newPerson.password), hashSetOf("USER"))
        userRepository.save(user)
        return user.id
    }

}
