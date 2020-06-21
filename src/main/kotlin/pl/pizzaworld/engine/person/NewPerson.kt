package pl.pizzaworld.engine.person

data class NewPerson(val firstName: String, val lastName: String, val userName: String, val password: String, val email: String) {
    fun validate(): Boolean {
        return true
    }

}
