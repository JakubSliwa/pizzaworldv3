package pl.pizzaworld.engine.application.security

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import kotlin.collections.HashSet

@Document
open class User(var firstName: String = "",
                var lastName: String = "",
                var userName: String = "",
                var email: String = "",
                var pass: String = "") {
    @Id
    var id: UUID = UUID.randomUUID()
    var version: Int = 0
    var accountNonExpired: Boolean = true
    var accountNonLocked: Boolean = true
    var credentialsNonExpired: Boolean = true
    var enabled: Boolean = true
    var roles: Set<Role> = HashSet()
    constructor(user: User) : this(user.firstName, user.lastName, user.userName, user.email, user.pass) {
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