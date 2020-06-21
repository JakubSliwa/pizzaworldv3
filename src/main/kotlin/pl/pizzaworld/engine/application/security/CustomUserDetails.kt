package pl.pizzaworld.engine.application.security


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

open class CustomUserDetails : User, UserDetails {

    constructor(user: User) : super(user)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles
                .stream()
                .map { role ->
                    SimpleGrantedAuthority(role)
                }
                .collect(Collectors.toList())
    }


    override fun getPassword(): String {
        return super.pass
    }

    override fun getUsername(): String {
        return super.userName
    }

    override fun isEnabled(): Boolean {
        return super.enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return super.credentialsNonExpired
    }

    override fun isAccountNonExpired(): Boolean {
        return super.accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return super.accountNonLocked
    }
}