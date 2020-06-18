package pl.pizzaworld.engine.application.security

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, UUID> {
    fun findOneByUserName(userName: String): User?
}