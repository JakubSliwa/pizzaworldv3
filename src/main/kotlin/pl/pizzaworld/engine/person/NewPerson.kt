package pl.pizzaworld.engine.person

data class NewPerson(val firstName: String, val lastName: String, val userName: String, val password: String, val email: String) {
    fun validate(): Boolean {
        return isEmailValid(email)
    }


    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email);
        }
    }
}
