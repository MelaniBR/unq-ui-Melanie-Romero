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
    var error : Boolean = false,
    var mensaje : String = "",
    var user : User? = null


){
    fun validateLogin(){
        try {
            //InstagramApp.logInOk(modelObject.instagramSystem.login(modelObject.email, modelObject.password));
            user = instagramSystem.login(email, password)
            error = false;
            mensaje = ""
        } catch (e: NotFound) {
            user = null;
            error = true
            mensaje = "Login incorrecto."
        }
    }

}