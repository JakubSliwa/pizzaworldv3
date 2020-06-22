package pl.pizzaworld.engine.application.security

import java.util.*

interface UserRepository{
    fun findOneByUserName(userName: String): User?
    fun save(user: User): UUID
}