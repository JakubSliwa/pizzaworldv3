package pl.pizzaworld.engine.application.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationService {
    val EXPIRATIONTIME: Long = 86400000 // 1 day in milliseconds
    val SIGNINGKEY = "SecretKey"
    val PREFIX = "Bearer"

    //dodać time provider
    fun addToken(res: HttpServletResponse, username: String?) {
        val token = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATIONTIME))
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