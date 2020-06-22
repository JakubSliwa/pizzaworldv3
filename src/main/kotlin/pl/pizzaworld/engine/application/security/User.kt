package pl.pizzaworld.engine.application.security

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "users")
open class User(var firstName: String = "",
                var lastName: String = "",
                @Indexed(unique = true) var userName: String = "",
                @Indexed(unique = true) var email: String = "",
                var pass: String = "",
                var roles: Set<String> = hashSetOf()) {
    @Id
    var id: UUID = UUID.randomUUID()
    var version: Int = 0
    var accountNonExpired: Boolean = true
    var accountNonLocked: Boolean = true
    var credentialsNonExpired: Boolean = true
    var enabled: Boolean = true

    constructor(user: User) : this(user.firstName, user.lastName, user.userName, user.email, user.pass, user.roles) {
        id = user.id
        version = user.version
        firstName = user.firstName
        lastName = user.lastName
        userName = user.userName
        email = user.email
        pass = user.pass
        accountNonExpired = user.accountNonExpired
        accountNonLocked = user.accountNonLocked
        credentialsNonExpired = user.credentialsNonExpired
        enabled = user.enabled
        roles = user.roles
    }
}