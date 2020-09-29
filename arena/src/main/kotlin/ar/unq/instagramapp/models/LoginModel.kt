package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginModel(
    val instagramSystem: InstagramSystem,
    var email: String = "",
    var password:String = "",
    var user : User? = null


){

    fun validateLoginForm(){
        validationEmail.validate(email)
        validationPassword.validate(password)
        validationLogin.validate("")
    }

    val isValidLoginForm: Boolean
        get() = !validationEmail.hasError && !validationPassword.hasError && !validationLogin.hasError

    val validationEmail: ArrayValidation<String> = object : ArrayValidation<String> (
        listOf(
            RequiredValidation("Debes ingresar una direccion de Email"),
            EmailValidation("Debes ingresar una direccion de Email valida")
        )
    ){}

    val validationPassword:  RequiredValidation = RequiredValidation("Debes ingresar una contraseña")

    val validationLogin: BasicValidation<String> = object : BasicValidation<String>("Login incorrecto" ) {
        override fun validate(value: String) {
            try {
                user = instagramSystem.login(email, password)
                hasError = false
            } catch (e: NotFound) {
                user = null;
                hasError = true
            }
        }
    }

}