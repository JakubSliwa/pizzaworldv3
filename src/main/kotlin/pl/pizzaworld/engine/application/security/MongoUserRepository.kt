package pl.pizzaworld.engine.application.security

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import java.util.*

class MongoUserRepository(private val mongoTemplate: MongoTemplate) : UserRepository {

    override fun findOneByUserName(userName: String): User? {
        val query = Query().addCriteria(User::userName isEqualTo userName)
        return mongoTemplate.findOne(query, User::class.java)
    }

    override fun save(user: User): UUID {
        return mongoTemplate.save(user).id
    }
}

