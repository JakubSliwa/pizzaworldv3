package pl.pizzaworld.engine.application.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import pl.pizzaworld.engine.application.time.TimeProvider
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationService(private val timeProvider: TimeProvider, private val userRepository: UserRepository) {
    val EXPIRATIONTIME: Long = 86400000 // 1 day in milliseconds
    val SIGNINGKEY = "SecretKey"
    val PREFIX = "Bearer"

    fun addToken(res: HttpServletResponse, username: String?) {
        val token = Jwts.builder()
                .setSubject(username)
                .setExpiration(timeProvider.now()?.toEpochSecond()?.plus(EXPIRATIONTIME)?.let { Date(it) })
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact()
        res.addHeader("Authorization", "$PREFIX $token")
        res.addHeader("Access-Control-Expose-Headers", "Authorization")
    }

    //sprawdzić czy user istnieje w bazie?
    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader("Authorization")
        if (token != null) {
            val user = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .body
                    .subject
            if (user != null) return UsernamePasswordAuthenticationToken(user, null, emptyList())
        }
        return null
    }
}