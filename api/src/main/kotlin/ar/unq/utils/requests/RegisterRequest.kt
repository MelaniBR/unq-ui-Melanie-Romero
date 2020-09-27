package ar.unq.utils.requests

data class RegisterRequest (
        val name: String,
        val email: String,
        val password: String,
        val image: String
) {
}