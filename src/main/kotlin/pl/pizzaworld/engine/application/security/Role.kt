package pl.pizzaworld.engine.application.security

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document
class Role(var rolename: String = "") {
    @Id
    val id: UUID? = null
}