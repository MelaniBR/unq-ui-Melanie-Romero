package ar.unq.utils.responses

data class ErrorResponse(
        val message: String,
        val result: String = "error"
) {
}