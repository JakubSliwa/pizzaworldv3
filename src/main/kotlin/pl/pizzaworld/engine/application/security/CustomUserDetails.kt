package pl.pizzaworld.engine.application.security

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomUserDetails : MongoRepository<Role, UUID> {
    fun findByRolename(rolename: String): Role
}