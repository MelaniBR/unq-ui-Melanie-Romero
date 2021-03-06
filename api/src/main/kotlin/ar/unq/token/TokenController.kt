package ar.unq.token;

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider
import org.unq.ui.model.User

class NotValidToken : Exception("Not valid token")

public class TokenController {
    val algorithm : Algorithm = Algorithm.HMAC256("very_secret")

    val generator : JWTGenerator<User> = JWTGenerator<User> { user: User, alg: Algorithm? ->
        val token : JWTCreator.Builder = JWT.create()
            .withClaim("id", user.id)
        token.sign(alg)
    }

    val verifier : JWTVerifier = JWT.require(algorithm).build()

    val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: User): String {
        return provider.generateToken(user)
    }

    fun validateToken(token: String?): String {
        val decodedJWT =  provider.validateToken(token)
        if (decodedJWT.isPresent() && decodedJWT.get().claims.contains("id")) {
            return decodedJWT.get().getClaim("id").asString()
        }
        throw NotValidToken()
    }

}
